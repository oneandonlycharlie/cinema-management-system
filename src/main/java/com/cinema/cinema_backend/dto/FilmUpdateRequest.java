package com.cinema.cinema_backend.dto;

import com.cinema.cinema_backend.model.Actor;
import com.cinema.cinema_backend.model.Director;
import com.cinema.cinema_backend.model.Genre;

import java.util.Set;

public class FilmUpdateRequest {

    private String name;             // 可为 null
    private Integer length;          // 可为 null
    private Genre genre;             // 可为 null
    private String intro;            // 可为 null
    private Director director;       // 可为 null
    private Set<Actor> actors;       // 可为 null
    private Double rating;           // 可为 null

    // getters & setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }

    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }

    public Set<Actor> getActors() { return actors; }
    public void setActors(Set<Actor> actors) { this.actors = actors; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
