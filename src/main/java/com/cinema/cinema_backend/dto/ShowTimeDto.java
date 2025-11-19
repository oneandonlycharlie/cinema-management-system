package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShowTimeDto {
    private Long id;
    private Long hallId;
    private String hallName;
    private Long filmId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> seatIds;
    private BigDecimal price;


    public ShowTimeDto(Long id, Long hallId, String hallName, Long filmId, LocalDateTime startTime, LocalDateTime endTime, List<Long> seatIds, BigDecimal price) {
        this.id = id;
        this.hallId = hallId;
        this.hallName = hallName;
        this.filmId = filmId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatIds = seatIds;
        this.price = price;
    }

    public ShowTimeDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
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

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
