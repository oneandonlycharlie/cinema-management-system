package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
import com.cinema.cinema_backend.model.CinemaUser;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.repository.FilmRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public List<Film> findAllFilms(){
        return filmRepository.findAll();
    }

    public Optional<Film> findFilmById(Long id){
        return filmRepository.findById(id);
    }

    public Film updateFilmById(Long id, FilmUpdateRequest request){
        Film foundFilm = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No film found by id " + id));

        if (request.getName() != null) foundFilm.setName(request.getName());
        if (request.getLength() != null) foundFilm.setLength(request.getLength());
        if (request.getGenre() != null) foundFilm.setGenre(request.getGenre());
        if (request.getIntro() != null) foundFilm.setIntro(request.getIntro());
        if (request.getDirector() != null) foundFilm.setDirector(request.getDirector());
        if (request.getActors() != null) foundFilm.setActors(request.getActors());
        if (request.getRating() != null) foundFilm.setRating(request.getRating());

        return filmRepository.save(foundFilm);
    }

    public boolean deleteFilmById(Long id){
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
