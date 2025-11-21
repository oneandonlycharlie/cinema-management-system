// TicketInfoDto.java
package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDto {
    private Long ticketId;
    private Long showtimeId;
    private String filmName;
    private String hallName;
    private Long seatNumber;
    private LocalDateTime showtime;
    private BigDecimal price;

    public TicketDto() {}

    public TicketDto(Long ticketId, Long showtimeId, String filmName, String hallName, Long seatNumber, LocalDateTime showtime, BigDecimal price) {
        this.ticketId = ticketId;
        this.showtimeId = showtimeId;
        this.filmName = filmName;
        this.hallName = hallName;
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.price = price;
    }

    // getters & setters
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public Long getShowtimeId() { return showtimeId; }
    public void setShowtimeId(Long showtimeId) { this.showtimeId = showtimeId; }
    public String getFilmName() { return filmName; }
    public void setFilmName(String filmName) { this.filmName = filmName; }
    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }
    public Long getSeatNumber() { return seatNumber; }
    public void setSeatNumber(Long seatNumber) { this.seatNumber = seatNumber; }
    public LocalDateTime getShowtime() { return showtime; }
    public void setShowtime(LocalDateTime showtime) { this.showtime = showtime; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
