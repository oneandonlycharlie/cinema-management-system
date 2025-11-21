package com.cinema.cinema_backend.service;


import com.cinema.cinema_backend.dto.OrderCreateRequest;
import com.cinema.cinema_backend.dto.OrderDto;
import com.cinema.cinema_backend.dto.OrderUpdateRequest;
import com.cinema.cinema_backend.model.*;
import com.cinema.cinema_backend.repository.CinemaUserRepository;
import com.cinema.cinema_backend.repository.OrderRepository;
import com.cinema.cinema_backend.repository.ShowTimeRepository;
import com.cinema.cinema_backend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ShowTimeRepository showTimeRepository;
    private final CinemaUserRepository cinemaUserRepository;
    private final TicketService ticketService;

    public OrderService(OrderRepository orderRepository, ShowTimeRepository showTimeRepository, CinemaUserRepository cinemaUserRepository, TicketService ticketService) {
        this.orderRepository = orderRepository;
        this.showTimeRepository = showTimeRepository;
        this.cinemaUserRepository = cinemaUserRepository;
        this.ticketService = ticketService;
    }

    // Create
    @Transactional
    public OrderDto createOrder(OrderCreateRequest request, Long userId) {
        ShowTime showtime = showTimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        CinemaUser user = cinemaUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setShowTime(showtime);
        order.setSeatCount(request.getSeatCount());
        order.setTotalAmount(showtime.getPrice().multiply(BigDecimal.valueOf(request.getSeatCount())));
        orderRepository.save(order);

        return new OrderDto(
                order.getId(),
                order.getStatus().name(),
                order.getTotalAmount(),
                order.getSeatCount(),
                showtime.getFilm().getName(),
                showtime.getStartTime(),
                showtime.getHall().getName()
        );
    }



    // Read all
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    // Read by id
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    // Partial update
    public Order updateOrder(Long id, OrderUpdateRequest request) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));

        request.getStatus().ifPresent(existing::setStatus);

        return orderRepository.save(existing);
    }

    // Delete
    public boolean deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public OrderDto payOrder(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

        List<Ticket> tickets = ticketService.createTickets(order);

        ShowTime showtime = order.getShowTime();

        return new OrderDto(
                order.getId(),
                order.getStatus().name(),
                order.getTotalAmount(),
                order.getSeatCount(),
                showtime.getFilm().getName(),
                showtime.getStartTime(),
                showtime.getHall().getName()
        );
    }

}

