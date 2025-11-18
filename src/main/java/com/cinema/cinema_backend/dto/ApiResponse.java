package com.cinema.cinema_backend.dto;

public record ApiResponse<T>(
        T data,
        String message,
        String error
) {}