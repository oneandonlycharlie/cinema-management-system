package com.cinema.cinema_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class Film {
    private Long id;
    private  String name;
    private int length;
    private String genre;
    private String intro;
    private String director;
    private String[] actors;
    private double rating;
    private double price;

    public Film(){
    }

    public Film(Long id,String name, int length, String genre, String intro, String director, String[] actors, double rating, double price){
        this.id = id;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.intro = intro;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getGenre() {
        return genre;
    }

    public String getIntro() {
        return intro;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
