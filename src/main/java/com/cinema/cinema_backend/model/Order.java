package com.cinema.cinema_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CinemaUser cinemaUser;

    @OneToMany(mappedBy = "order")
    private Set<Ticket> tickets;

    @OneToOne(mappedBy = "order")
    private Payment payments;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal totalAmount;

    public Order() {

    }

    public Order(Long id, LocalDateTime createdAt, Set<Ticket> tickets, CinemaUser cinemaUser, OrderStatus status, Payment payments, BigDecimal totalAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.tickets = tickets;
        this.cinemaUser = cinemaUser;
        this.status = status;
        this.payments = payments;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CinemaUser getUser() {
        return cinemaUser;
    }

    public void setUser(CinemaUser cinemaUser) {
        this.cinemaUser = cinemaUser;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
