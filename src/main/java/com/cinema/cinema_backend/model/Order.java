package com.cinema.cinema_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    private long id;
    private LocalDateTime createdAt;
    private User user;
    private String[] tickets;
    private BigDecimal totalAmount;
    private String[] payments;
    private String status;

    public Order() {

    }

    public Order(long id, LocalDateTime createdAt, User user, String[] tickets, BigDecimal totalAmount, String[] payments, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.tickets = tickets;
        this.totalAmount = totalAmount;
        this.payments = payments;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public String[] getTickets() {
        return tickets;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String[] getPayments() {
        return payments;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPayments(String[] payments) {
        this.payments = payments;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
