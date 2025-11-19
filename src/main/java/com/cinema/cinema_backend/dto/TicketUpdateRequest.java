package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;

public class TicketUpdateRequest {
    private Long seatId;          // null 表示不更新
    private Boolean isAvailable;
    private BigDecimal price;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
