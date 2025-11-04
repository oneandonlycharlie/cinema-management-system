package com.cinema.cinema_backend.dto;

public record LoginRequest(String usernameOrEmail, String password) {
}
