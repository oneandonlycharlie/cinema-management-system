package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShowTimeCreateRequest {

    private Long hallId;
    private LocalDateTime startTime;
    private List<Long> seatIds;
    private BigDecimal price;

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

