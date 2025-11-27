package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private String status;
    private BigDecimal totalAmount;
    private int seatCount;
    private String filmName;
    private LocalDateTime showtimeStart;
    private String hallName;

    public OrderDto() {}

    public OrderDto(Long id, String status, BigDecimal totalAmount, int seatCount,
                    String filmName, LocalDateTime showtimeStart, String hallName) {
        this.id = id;
        this.status = status;
        this.totalAmount = totalAmount;
        this.seatCount = seatCount;
        this.filmName = filmName;
        this.showtimeStart = showtimeStart;
        this.hallName = hallName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }

    public String getFilmName() { return filmName; }
    public void setFilmName(String filmName) { this.filmName = filmName; }

    public LocalDateTime getShowtimeStart() { return showtimeStart; }
    public void setShowtimeStart(LocalDateTime showtimeStart) { this.showtimeStart = showtimeStart; }

    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }
}
