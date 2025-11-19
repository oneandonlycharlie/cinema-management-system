package com.cinema.cinema_backend.dto;

import com.cinema.cinema_backend.model.OrderStatus;
import java.util.Optional;

public class OrderUpdateRequest {

    private Optional<OrderStatus> status = Optional.empty();

    public OrderUpdateRequest() {}

    public Optional<OrderStatus> getStatus() {
        return status;
    }

    public void setStatus(Optional<OrderStatus> status) {
        this.status = status;
    }
}

