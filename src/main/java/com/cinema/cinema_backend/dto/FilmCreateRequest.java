package com.cinema.cinema_backend.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

@NotNull
public class FilmCreateRequest {
    private String name;
    private int length;
    private String genre; // 可以改成枚举后在 Service 转
    private String intro;
    private String director; // 前端传字符串
    private Set<String> actors; // 前端传名字数组
    private double rating;

    // getter & setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre.toUpperCase(); }

    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Set<String> getActors() { return actors; }
    public void setActors(Set<String> actors) { this.actors = actors; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
