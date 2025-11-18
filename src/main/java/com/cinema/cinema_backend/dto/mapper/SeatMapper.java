package com.cinema.cinema_backend.dto.mapper;


import com.cinema.cinema_backend.dto.SeatDto;
import com.cinema.cinema_backend.model.Seat;

import java.util.stream.Collectors;

public class SeatMapper {

    public static SeatDto toDto(Seat entity) {
        SeatDto dto = new SeatDto();
        dto.setId(entity.getId());
        dto.setHallId(entity.getHall().getId());

        dto.setShowTimeIds(
                entity.getShowTimes().stream()
                        .map(st -> st.getId())
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
