package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmDto;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
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
        System.out.println(request);
        FilmDto filmDto = filmService.save(request);
        return ResponseEntity.status(201).body(new ApiResponse<FilmDto>(filmDto,  "Film created", null));
    }

    // read
    @GetMapping(path = "/films")
    public ResponseEntity<?> getAllFilms(){
        List<FilmDto> films = filmService.findAllFilms();
        ApiResponse<List<FilmDto>> response = new ApiResponse<>(films, "Fetched all films", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/films/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id){
        FilmDto filmDto = filmService.findFilmById(id);
        if (filmDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Film not found with id: " + id);
        }
        ApiResponse<FilmDto> response = new ApiResponse<>(filmDto, "Fetched film", null);
        return ResponseEntity.ok(response);
    }

    // update
    @PatchMapping(path = "/films/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Long id, @RequestBody FilmUpdateRequest request) {
        try {
            FilmDto updatedFilmDto = filmService.updateFilmById(id, request);
            return ResponseEntity.ok(new ApiResponse<>(updatedFilmDto, "Film updated", null));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>(null, null, "Film not found"));

        }
    }

    // delete
    @DeleteMapping(path = "/films/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        boolean deleted = filmService.deleteFilmById(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(null, "Film deleted successfully with id: " + id, null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(null, null, "Film not found with id: " + id));
        }
    }
}

