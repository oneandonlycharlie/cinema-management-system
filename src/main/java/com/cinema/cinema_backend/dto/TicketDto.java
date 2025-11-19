package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class TicketDto {
    private Long id;
    private Long showtimeId;
    private Long seatId;
    private BigDecimal price;
    private boolean isAvailable;

    public TicketDto() {
    }

    public TicketDto(Long id, Long showtimeId, Long seatId, BigDecimal price, boolean isAvailable) {
        this.id = id;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
