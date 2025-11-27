package com.cinema.cinema_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "showtimes")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "film_id")
    @JsonIgnore
    private Film film;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "showtimes_seats",
            joinColumns = @JoinColumn(name = "showtime_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private Set<Seat> seats = new LinkedHashSet<>();;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public ShowTime(Long id, Hall hall, Film film, LocalDateTime startTime, LocalDateTime endTime, Set<Seat> seats, BigDecimal price, Set<Ticket> tickets) {
        this.id = id;
        this.hall = hall;
        this.film = film;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.price = price;
        this.tickets = tickets;
    }

    public ShowTime() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Long> getSeatIds(){
        return seats.stream().map(Seat::getId).toList();
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
