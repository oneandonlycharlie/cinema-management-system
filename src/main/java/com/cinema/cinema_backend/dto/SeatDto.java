package com.cinema.cinema_backend.dto;

import java.util.List;

public class SeatDto {
    private  Long id;
    private Long hallId;
    private List<Long> showTimeIds;

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

    public List<Long> getShowTimeIds() {
        return showTimeIds;
    }

    public void setShowTimeIds(List<Long> showTimeIds) {
        this.showTimeIds = showTimeIds;
    }
}
