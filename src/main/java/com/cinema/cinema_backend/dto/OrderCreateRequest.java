package com.cinema.cinema_backend.dto;

public class OrderCreateRequest {
    private Long showtimeId;
    private int seatCount;

    public OrderCreateRequest() {}

    public OrderCreateRequest(Long showtimeId, int seatCount) {
        this.showtimeId = showtimeId;
        this.seatCount = seatCount;
    }

    public Long getShowtimeId() { return showtimeId; }
    public void setShowtimeId(Long showtimeId) { this.showtimeId = showtimeId; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }
}
