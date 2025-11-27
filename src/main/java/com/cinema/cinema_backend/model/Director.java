package com.cinema.cinema_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();

    public Director(){

    }

    public Director(String name, List<Film> films){
        this.name = name;
        this.films = films;
    }

    public Director(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
