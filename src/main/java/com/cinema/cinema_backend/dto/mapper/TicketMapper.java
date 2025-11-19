package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.TicketDto;
import com.cinema.cinema_backend.model.Ticket;

public class TicketMapper {
    public static TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setShowtimeId(ticket.getShowTime().getId());
        dto.setSeatId(ticket.getSeat().getId());
        dto.setPrice(ticket.getPrice());
        dto.setAvailable(ticket.isAvailable());
        return dto;
    }
}

