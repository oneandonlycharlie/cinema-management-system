package com.cinema.cinema_backend.dto;

import com.cinema.cinema_backend.model.*;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

public class FilmCreateRequest {

    @NotNull
    private String name;
    private int length;

    private Genre genre;
    @NotNull
    private String intro;

    private Director director;
    private Set<Actor> actors = new LinkedHashSet<>();
    private double rating;

    public FilmCreateRequest(String name, int length, Genre genre, String intro, Director director, Set<Actor> actors, double rating) {
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.intro = intro;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
