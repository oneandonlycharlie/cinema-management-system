package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public Film save(FilmCreateRequest request){
        Film film = new Film();
        film.setName(request.getName());
        film.setLength(request.getLength());
        film.setGenre(request.getGenre());
        film.setIntro(request.getIntro());
        film.setRating(request.getRating());
        film.setDirector(request.getDirector());
        film.setActors(request.getActors());

        return filmRepository.save(film);
    }

    public Optional<Film> findFilmByName(String name){
        return filmRepository.findFilmByName(name);
    }

}
