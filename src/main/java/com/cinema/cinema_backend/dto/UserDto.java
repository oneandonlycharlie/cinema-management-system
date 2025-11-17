package com.cinema.cinema_backend.dto;

public record UserDto(
        Long id,
        String name,
        String email,
        String role
) {}
