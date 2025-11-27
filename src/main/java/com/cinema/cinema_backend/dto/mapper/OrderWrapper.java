package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.OrderWithTicketsDto;
import com.cinema.cinema_backend.dto.TicketDto;
import com.cinema.cinema_backend.model.Order;
import java.util.List;
import java.util.stream.Collectors;

public class OrderWrapper {
    public static OrderWithTicketsDto toDto(Order order) {
        if (order == null) return null;

        List<TicketDto> tickets = order.getTickets().stream()
                .map(TicketWrapper::toDto)
                .collect(Collectors.toList());

        return new OrderWithTicketsDto(
                order.getId(),
                order.getStatus().name(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                tickets
        );
    }
}

