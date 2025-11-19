package com.cinema.cinema_backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genre {
    DRAMA,
    COMEDY,
    ROMANCE,
    HORROR,
    DOCUMENTARY,
    ACTION,
    SCI_FI,
    ANIMATION;

    @JsonCreator
    public static Genre fromString(String value) {
        return Genre.valueOf(value.toUpperCase());
    }
}
