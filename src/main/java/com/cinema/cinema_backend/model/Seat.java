package com.cinema.cinema_backend.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "seat")
    private Set<Ticket> tickets = new LinkedHashSet<>();;

    @ManyToMany(mappedBy = "seats")
    private Set<ShowTime> showTimes;

    public Seat(){

    }
    public Seat(Long id, Hall hall, Set<Ticket> tickets, Set<ShowTime> showTimes) {
        this.id = id;
        this.hall = hall;
        this.tickets = tickets;
        this.showTimes = showTimes;
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(Set<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }
}
