package com.cinema.cinema_backend.dto;

import com.cinema.cinema_backend.model.Genre;
import java.util.List;

public class FilmDto {
    private Long id;
    private String name;
    private int length;
    private Genre genre;
    private String intro;
    private double rating;

    private List<ShowTimeDto> showTimes;
    private String director;
    private List<String> actors;

    public FilmDto() {}

    public FilmDto(Long id, String name, int length, Genre genre, String intro, double rating, List<ShowTimeDto> showTimes, String director, List<String> actors) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.intro = intro;
        this.rating = rating;
        this.showTimes = showTimes;
        this.director = director;
        this.actors = actors;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<ShowTimeDto> getShowTimes() { return showTimes; }
    public void setShowTimes(List<ShowTimeDto> showTimes) { this.showTimes = showTimes; }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }
}
