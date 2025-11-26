package com.cinema.cinema_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int length;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String intro;
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    @JsonIgnore
//    add cascade type
    private Director director;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "films_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonIgnore
    private Set<Actor> actors = new LinkedHashSet<>();
    private double rating;

    @JsonIgnore
    @OneToMany(mappedBy = "film")
    private Set<ShowTime> showTimes = new LinkedHashSet<>();

    public Film() {
    }

    public Film(Long id, String name, int length, Genre genre, String intro, Director director, Set<Actor> actors, double rating, Set<ShowTime> showTimes, String coverImageUrl) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.intro = intro;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.showTimes = showTimes;
        this.coverImageUrl = coverImageUrl;
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

    public Genre getGenre() {
        return genre;
    }

    public String getIntro() {
        return intro;
    }

    public Director getDirector() {
        return director;
    }

    public double getRating() {
        return rating;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<ShowTime> getShowTimes() {
        return showTimes;
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

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }


    public void setShowTimes(Set<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}



