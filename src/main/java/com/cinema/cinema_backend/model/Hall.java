package com.cinema.cinema_backend.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name= "halls")
public class Hall{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShowTime> showTimes = new LinkedHashSet<>();;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new LinkedHashSet<>();;

    public Hall(){
    }

    public Hall(Long id, String name, int capacity, Set<ShowTime> showTimes, Set<Seat> seats) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.showTimes = showTimes;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public Set<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public void setShowTimes(Set<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }
}
