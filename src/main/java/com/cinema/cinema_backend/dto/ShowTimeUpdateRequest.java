package com.cinema.cinema_backend.dto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class ShowTimeUpdateRequest {

    private Optional<Long> hallId = Optional.empty();
    private Optional<Long> filmId = Optional.empty();
    private Optional<LocalDateTime> startTime = Optional.empty();
    private Optional<LocalDateTime> endTime = Optional.empty();

    public ShowTimeUpdateRequest() {
    }

    public Optional<Long> getHallId() {
        return hallId;
    }

    public void setHallId(Optional<Long> hallId) {
        this.hallId = hallId;
    }

    public Optional<Long> getFilmId() {
        return filmId;
    }

    public void setFilmId(Optional<Long> filmId) {
        this.filmId = filmId;
    }

    public Optional<LocalDateTime> getStartTime() {
        return startTime;
    }

    public void setStartTime(Optional<LocalDateTime> startTime) {
        this.startTime = startTime;
    }

    public Optional<LocalDateTime> getEndTime() {
        return endTime;
    }

    public void setEndTime(Optional<LocalDateTime> endTime) {
        this.endTime = endTime;
    }
}
