package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmDto;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
import com.cinema.cinema_backend.dto.mapper.FilmWrapper;
import com.cinema.cinema_backend.model.*;
import com.cinema.cinema_backend.repository.ActorRepository;
import com.cinema.cinema_backend.repository.DirectorRepository;
import com.cinema.cinema_backend.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    public FilmService(FilmRepository filmRepository, DirectorRepository directorRepository, ActorRepository actorRepository){
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
    }

    public FilmDto save(FilmCreateRequest request){
        Director director = directorRepository.findByName(request.getDirector())
                .orElseGet(() -> {
                    Director newDirector = new Director();
                    newDirector.setName(request.getDirector());
                    return directorRepository.save(newDirector);
                });

        // 2. 处理演员
        Set<Actor> actors = request.getActors().stream()
                .map(name -> actorRepository.findByName(name)
                        .orElseGet(() -> actorRepository.save(new Actor(name))))
                .collect(Collectors.toSet());

        // 3. 创建电影
        Film film = new Film();
        film.setName(request.getName());
        film.setIntro(request.getIntro());
        film.setLength(request.getLength());
        film.setRating(request.getRating());
        film.setGenre(Genre.valueOf(request.getGenre()));
        film.setDirector(director); // 设置导演
        film.setActors(actors);     // 设置演员

        Film saved = filmRepository.save(film);

        return FilmWrapper.toDto(saved);
    }

    public Optional<Film> findFilmByName(String name){
        return filmRepository.findFilmByName(name);
    }

    public List<FilmDto> findAllFilms(){
        return filmRepository.findAll()
                .stream()
                .map(FilmWrapper::toDto)
                .toList();
    }

    public FilmDto findFilmById(Long id){
        return filmRepository.findById(id)
                .map(FilmWrapper::toDto)
                .orElse(null);
    }

    public FilmDto updateFilmById(Long id, FilmUpdateRequest request){
        Film foundFilm = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No film found by id " + id));

        if (request.getName() != null) foundFilm.setName(request.getName());
        if (request.getLength() != null) foundFilm.setLength(request.getLength());
        if (request.getGenre() != null) foundFilm.setGenre(request.getGenre());
        if (request.getIntro() != null) foundFilm.setIntro(request.getIntro());
        if (request.getDirector() != null) foundFilm.setDirector(request.getDirector());
        if (request.getActors() != null) foundFilm.setActors(request.getActors());
        if (request.getRating() != null) foundFilm.setRating(request.getRating());
        Film updated = filmRepository.save(foundFilm);
        return FilmWrapper.toDto(updated);
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
