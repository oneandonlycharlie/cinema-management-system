package com.cinema.cinema_backend.dto.mapper;

import com.cinema.cinema_backend.dto.FilmDto;
import com.cinema.cinema_backend.dto.ShowTimeDto;
import com.cinema.cinema_backend.model.Actor;
import com.cinema.cinema_backend.model.Film;

import java.util.List;

public class FilmWrapper {

    public static FilmDto toDto(Film film){
        List<String> actors = film.getActors()
                .stream()
                .map(Actor::getName)
                .toList();

        List<ShowTimeDto> showTimeDtos = film.getShowTimes()
                .stream()
                .map(showTime -> new ShowTimeDto(
                        showTime.getId(),
                        showTime.getHall().getId(),
                        showTime.getHall().getName(),
                        film.getId(),
                        showTime.getStartTime(),
                        showTime.getEndTime(),
                        showTime.getSeatIds(),
                        showTime.getPrice()
                ))
                .toList();

        return new FilmDto(
                film.getId(),
                film.getName(),
                film.getLength(),
                film.getGenre(),
                film.getIntro(),
                film.getRating(),
                showTimeDtos,
                film.getDirector() != null ? film.getDirector().getName() : null,
                actors,
                film.getCoverImageUrl()
        );
    }
}
