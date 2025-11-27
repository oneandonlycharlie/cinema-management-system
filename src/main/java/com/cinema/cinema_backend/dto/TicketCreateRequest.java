package com.cinema.cinema_backend.dto;

public class TicketCreateRequest {
    private Long showtimeId;
    private int seatCount;

    public Long getShowtimeId() { return showtimeId; }
    public void setShowtimeId(Long showtimeId) { this.showtimeId = showtimeId; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }
}
