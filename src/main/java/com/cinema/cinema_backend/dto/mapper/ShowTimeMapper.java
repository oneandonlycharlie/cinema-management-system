package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.ShowTimeDto;
import com.cinema.cinema_backend.model.Seat;
import com.cinema.cinema_backend.model.ShowTime;
import org.springframework.security.core.userdetails.memory.UserAttribute;

import java.util.stream.Collectors;

public class ShowTimeMapper {
    public static ShowTimeDto toDto(ShowTime showTime) {
        ShowTimeDto dto = new ShowTimeDto();
        dto.setId(showTime.getId());
        dto.setHallId(showTime.getHall().getId());
        dto.setFilmId(showTime.getFilm().getId());
        dto.setStartTime(showTime.getStartTime());
        dto.setEndTime(showTime.getEndTime());
        dto.setPrice(showTime.getPrice());
        dto.setHallName(showTime.getHall().getName());

        dto.setSeatIds(
                showTime.getSeats()
                        .stream()
                        .map(Seat::getId)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
