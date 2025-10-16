package com.cinema.cinema_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.memory.UserAttribute;

import java.util.List;

@Entity
@Table(name= "halls")
public class Hall {
    private long id;
    private String name;
    private int capacity;
    private String[] showtimes;
    private String[] seats;

    public Hall(){

    }

    public Hall(long id, String name, int capacity, String[] showtimes, String[] seats){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.showtimes = showtimes;
        this.seats = seats;
    }
}
