package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.TicketUpdateRequest;
import com.cinema.cinema_backend.model.*;
import com.cinema.cinema_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final OrderRepository orderRepository;
    private final ShowTimeRepository showTimeRepository;

    public TicketService(TicketRepository ticketRepository,
                         SeatRepository seatRepository,
                         OrderRepository orderRepository,
                         ShowTimeRepository showTimeRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.orderRepository = orderRepository;
        this.showTimeRepository = showTimeRepository;
    }

    private void updateOrderTotalAmount(Order order) {
        BigDecimal total = ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getOrder() != null && ticket.getOrder().getId().equals(order.getId()))
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);
        orderRepository.save(order);
    }

    // Create
    @Transactional
    public List<Ticket> createTickets(Order order) {
        Long showtimeId = order.getShowTime().getId();
        int seatCount = order.getSeatCount();
        ShowTime showTime = showTimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NoSuchElementException("ShowTime not found with id: " + showtimeId));

        List<Seat> seats = new ArrayList<>(showTime.getSeats());

        List<Long> soldSeatIds = ticketRepository.findByShowTimeId(showtimeId)
                .stream()
                .map(ticket -> ticket.getSeat().getId())   // 每张票只有一个 seat
                .toList();

        List<Seat> availableSeats = seats.stream()
                .filter(seat -> !soldSeatIds.contains(seat.getId()))
                .collect(Collectors.toList());

        if (availableSeats.size() < seatCount) {
            throw new RuntimeException("Not enough available seats");
        }

        Collections.shuffle(availableSeats);

        List<Ticket> createdTickets = new ArrayList<>();

        for (int i = 0; i < seatCount; i++) {
            Seat selectedSeat = availableSeats.get(i);

            Ticket t = new Ticket();
            t.setShowTime(showTime);
            t.setSeat(selectedSeat);
            t.setPrice(showTime.getPrice());
            t.setAvailable(true);
            t.setOrder(order);

            createdTickets.add(ticketRepository.save(t));
        }

        return createdTickets;
    }



    // Read all
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    // Read by id
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    // Partial update
    @Transactional
    public Ticket updateTicket(Long id, TicketUpdateRequest request) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));

        if (request.getSeatId() != null) {
            Seat seat = seatRepository.findById(request.getSeatId())
                    .orElseThrow(() -> new NoSuchElementException("Seat not found with id: " + request.getSeatId()));
            existing.setSeat(seat);
        }

        if (request.getIsAvailable() != null) {
            existing.setAvailable(request.getIsAvailable());
        }

        if (request.getPrice() != null) {
            existing.setPrice(request.getPrice());
        }

        Ticket saved = ticketRepository.save(existing);

        if (saved.getOrder() != null) {
            updateOrderTotalAmount(saved.getOrder());
        }

        return saved;
    }

    // Delete
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            Order order = ticket.getOrder();
            ticketRepository.deleteById(id);
            if (order != null) {
                updateOrderTotalAmount(order);
            }
            return true;
        }
        return false;
    }
}
