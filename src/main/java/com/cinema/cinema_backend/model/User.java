package com.cinema.cinema_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String[] tickets;

    public User() {

    }

    public User(Long id, String email, String password, String role, String[] tickets) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String[] getTickets() {
        return tickets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }
}
