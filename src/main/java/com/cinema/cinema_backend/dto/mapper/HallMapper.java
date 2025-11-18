package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.HallDto;
import com.cinema.cinema_backend.model.Hall;
import com.cinema.cinema_backend.model.Seat;
import com.cinema.cinema_backend.model.ShowTime;
import java.util.stream.Collectors;

public class HallMapper {

    public static HallDto toDto(Hall entity) {
        HallDto dto = new HallDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCapacity(entity.getCapacity());
        dto.setSeatIds(
                entity.getSeats().stream()
                        .map(Seat::getId)
                        .collect(Collectors.toList())

        );

        dto.setShowTimeIds((
                entity.getShowTimes().stream()
                        .map(ShowTime::getId)
                        .collect(Collectors.toList())
        ));
        return dto;
    }
}
