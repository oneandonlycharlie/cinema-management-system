package com.cinema.cinema_backend.dto;

import java.util.List;

public class HallDto {
    private Long id;
    private String name;
    private int capacity;
    private List<Long> seatIds;
    private List<Long> showTimeIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public List<Long> getShowTimeIds() {
        return showTimeIds;
    }

    public void setShowTimeIds(List<Long> showTimeIds) {
        this.showTimeIds = showTimeIds;
    }
}
