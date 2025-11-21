package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.TicketDto;
import com.cinema.cinema_backend.model.Ticket;

public class TicketMapper {
    public static TicketDto toDto(Ticket ticket) {
        if (ticket == null) return null;

        return new TicketDto(
                ticket.getId(),
                ticket.getShowTime().getId(),
                ticket.getShowTime().getFilm().getName(),
                ticket.getShowTime().getHall().getName(),
                ticket.getSeat().getId(),
                ticket.getShowTime().getStartTime(),
                ticket.getPrice()
        );
    }
}
