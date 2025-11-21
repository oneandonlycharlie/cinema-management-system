package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderWithTicketsDto {
    private Long orderId;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private List<TicketDto> tickets;

    public OrderWithTicketsDto() {}

    public OrderWithTicketsDto(Long orderId, String status, BigDecimal totalAmount, LocalDateTime createdAt, List<TicketDto> tickets) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.tickets = tickets;
    }

    // getters & setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<TicketDto> getTickets() { return tickets; }
    public void setTickets(List<TicketDto> tickets) { this.tickets = tickets; }
}
