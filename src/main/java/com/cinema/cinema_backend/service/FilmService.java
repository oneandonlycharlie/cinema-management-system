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
                .orElseThrow(()-> new NoSuchElementException("No film found by id " + id));

        request.getName().ifPresent(foundFilm::setName);
        request.getLength().ifPresent(foundFilm::setLength);
        request.getGenre().ifPresent(foundFilm::setGenre);
        request.getIntro().ifPresent(foundFilm::setIntro);
        request.getDirector().ifPresent(foundFilm::setDirector);
        request.getActors().ifPresent(foundFilm::setActors);
        request.getRating().ifPresent(foundFilm::setRating);

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
