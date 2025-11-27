package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmDto;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
import com.cinema.cinema_backend.service.FilmService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
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
        FilmDto films = filmService.findFilmById(id);
        ApiResponse<FilmDto> response = new ApiResponse<>(films, "Fetched all films", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/films", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmDto> createFilm(
            @RequestPart("data") FilmCreateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        FilmDto filmDto = filmService.createFilm(request, image);
        return ResponseEntity.ok(filmDto);
    }

    @PatchMapping(value = "/films/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmDto> updateFilm(
            @PathVariable Long id,
            @RequestPart("data") FilmUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        FilmDto filmDto = filmService.updateFilm(id, request, image);
        return ResponseEntity.ok(filmDto);
    }

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

