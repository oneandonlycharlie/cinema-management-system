package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.TicketUpdateRequest;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.model.Order;
import com.cinema.cinema_backend.model.Seat;
import com.cinema.cinema_backend.model.Ticket;
import com.cinema.cinema_backend.repository.FilmRepository;
import com.cinema.cinema_backend.repository.OrderRepository;
import com.cinema.cinema_backend.repository.SeatRepository;
import com.cinema.cinema_backend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FilmRepository filmRepository;
    private final SeatRepository seatRepository;
    private final OrderRepository orderRepository;

    public TicketService(TicketRepository ticketRepository,
                         FilmRepository filmRepository,
                         SeatRepository seatRepository,
                         OrderRepository orderRepository) {
        this.ticketRepository = ticketRepository;
        this.filmRepository = filmRepository;
        this.seatRepository = seatRepository;
        this.orderRepository = orderRepository;
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
    public Ticket save(Ticket ticket) {
        Ticket saved = ticketRepository.save(ticket);
        if (saved.getOrder() != null) {
            updateOrderTotalAmount(saved.getOrder());
        }
        return saved;
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

        request.getIsAvailable().ifPresent(existing::setAvailable);

        request.getFilmId().ifPresent(filmId -> {
            Film film = filmRepository.findById(filmId)
                    .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + filmId));
            existing.setFilm(film);
        });

        request.getSeatId().ifPresent(seatId -> {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new NoSuchElementException("Seat not found with id: " + seatId));
            existing.setSeat(seat);
        });

        request.getPrice().ifPresent(existing::setPrice);

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
