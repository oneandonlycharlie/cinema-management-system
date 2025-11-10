package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.service.FilmService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @GetMapping(path = "films/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id){
        Optional<Film> film = filmService.findFilmById(id);
        if (film.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Film not found with id: " + id);
        }
        return ResponseEntity.ok(film.get());
    }

    // update
    @PatchMapping(path = "films/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Long id, @RequestBody FilmUpdateRequest request) {
        try {
            Film updated = filmService.updateFilmById(id, request);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // delete
    @DeleteMapping(path = "films/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        boolean deleted = filmService.deleteFilmById(id);
        if (deleted) {
            return ResponseEntity.ok("Film deleted successfully with id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Film not found with id: " + id);
        }
    }
}

