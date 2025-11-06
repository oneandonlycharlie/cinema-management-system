package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
