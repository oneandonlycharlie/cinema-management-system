package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping(path = "/films")
    public ResponseEntity<?> createFilm(@RequestBody @Valid FilmCreateRequest request){
        Film film = filmService.save(request);
        return ResponseEntity.ok(film);
    }

    // read
    @GetMapping(path = "/films")
    public ResponseEntity<?> getAllFilms(){
        List<Film> films = filmService.findAllFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping(path = "film/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id){
        Film film = filmService.findFilmById(id)
                .orElseThrow(()-> new NoSuchElementException("Film not found with id:" + id));
        return ResponseEntity.ok(film);
    }

    // update

    // delete
    @DeleteMapping(path = "film/{id}")
    public ResponseEntity<?> deleteFilmById(@PathVariable Long id) {
        boolean deleted = filmService.deleteFilmById(id);
        if (deleted) {
            return ResponseEntity.ok("Film deleted successfully with id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Film not found with id: " + id);
        }
    }
}

