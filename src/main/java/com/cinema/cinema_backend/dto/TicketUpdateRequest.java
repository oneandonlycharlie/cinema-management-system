package com.cinema.cinema_backend.dto;

import java.math.BigDecimal;
import java.util.Optional;

public class TicketUpdateRequest {

    private Optional<Boolean> isAvailable = Optional.empty();
    private Optional<Long> seatId = Optional.empty();
    private Optional<Long> filmId = Optional.empty();
    private Optional<BigDecimal> price = Optional.empty();

    public TicketUpdateRequest() {}

    public Optional<Boolean> getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Optional<Boolean> isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Optional<Long> getSeatId() {
        return seatId;
    }

    public void setSeatId(Optional<Long> seatId) {
        this.seatId = seatId;
    }

    public Optional<Long> getFilmId() {
        return filmId;
    }

    public void setFilmId(Optional<Long> filmId) {
        this.filmId = filmId;
    }


    public Optional<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(Optional<BigDecimal> price) {
        this.price = price;
    }
}
