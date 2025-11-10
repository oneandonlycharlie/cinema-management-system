package com.cinema.cinema_backend.dto;

import com.cinema.cinema_backend.model.Actor;
import com.cinema.cinema_backend.model.Director;
import com.cinema.cinema_backend.model.Genre;

import java.util.Optional;
import java.util.Set;

public class FilmUpdateRequest {
    private Optional<String> name = Optional.empty();
    private Optional<Integer> length = Optional.empty();
    private Optional<Genre> genre = Optional.empty();
    private Optional<String> intro = Optional.empty();
    private Optional<Director> director = Optional.empty();
    private Optional<Set<Actor>> actors = Optional.empty();
    private Optional<Double> rating = Optional.empty();

    public FilmUpdateRequest(Optional<String> name, Optional<Integer> length, Optional<Genre> genre, Optional<String> intro, Optional<Director> director, Optional<Set<Actor>> actors, Optional<Double> rating) {
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.intro = intro;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<Integer> getLength() {
        return length;
    }

    public void setLength(Optional<Integer> length) {
        this.length = length;
    }

    public Optional<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Optional<Genre> genre) {
        this.genre = genre;
    }

    public Optional<String> getIntro() {
        return intro;
    }

    public void setIntro(Optional<String> intro) {
        this.intro = intro;
    }

    public Optional<Director> getDirector() {
        return director;
    }

    public void setDirector(Optional<Director> director) {
        this.director = director;
    }

    public Optional<Set<Actor>> getActors() {
        return actors;
    }

    public void setActors(Optional<Set<Actor>> actors) {
        this.actors = actors;
    }

    public Optional<Double> getRating() {
        return rating;
    }

    public void setRating(Optional<Double> rating) {
        this.rating = rating;
    }
}
