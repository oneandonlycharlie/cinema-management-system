package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShowTimeUpdateRequest {

    private Long hallId;        // Optional 去掉
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal price;
    private Long FilmId;

    public ShowTimeUpdateRequest() {}

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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getFilmId() {
        return FilmId;
    }

    public void setFilmId(Long filmId) {
        FilmId = filmId;
    }
}
