package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.FilmCreateRequest;
import com.cinema.cinema_backend.dto.FilmDto;
import com.cinema.cinema_backend.dto.FilmUpdateRequest;
import com.cinema.cinema_backend.dto.mapper.FilmWrapper;
import com.cinema.cinema_backend.model.*;
import com.cinema.cinema_backend.repository.ActorRepository;
import com.cinema.cinema_backend.repository.DirectorRepository;
import com.cinema.cinema_backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
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

    public FilmDto createFilm(FilmCreateRequest request, MultipartFile image) throws IOException {

        Film film = new Film();

        film.setName(request.getName());
        film.setIntro(request.getIntro());
        film.setLength(request.getLength());
        film.setRating(request.getRating());

        if (request.getGenre() != null) {
            film.setGenre(Genre.valueOf(request.getGenre()));
        }

        // ✅ Director: String -> Entity
        if (request.getDirector() != null) {
            Director director = directorRepository
                    .findByName(request.getDirector())
                    .orElseGet(() -> directorRepository.save(
                            new Director(request.getDirector())
                    ));
            film.setDirector(director);
        }

        // ✅ Actors: Set<String> -> Set<Actor>
        if (request.getActors() != null) {
            Set<Actor> actorEntities = request.getActors()
                    .stream()
                    .map(name -> actorRepository
                            .findByName(name)
                            .orElseGet(() -> actorRepository.save(new Actor(name)))
                    )
                    .collect(Collectors.toSet());

            film.setActors(actorEntities);
        }

        // ✅ 图片处理
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            film.setCoverImageUrl(imageUrl);   // 前提：你 Film 里有 coverUrl 字段
        }

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

    public FilmDto updateFilm(Long filmId, FilmUpdateRequest request, MultipartFile image) throws IOException {

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found: " + filmId));

        // ✅ 允许部分字段更新
        if (request.getName() != null)
            film.setName(request.getName());

        if (request.getIntro() != null)
            film.setIntro(request.getIntro());

        if (request.getLength() != null)
            film.setLength(request.getLength());

        if (request.getRating() != null)
            film.setRating(request.getRating());

        if (request.getGenre() != null)
            film.setGenre(request.getGenre());

        // ✅ Director: 既支持传 Director 实体，也可以传名字
        if (request.getDirector() != null) {
            Director director = directorRepository
                    .findByName(request.getDirector().getName())
                    .orElseGet(() -> directorRepository.save(
                            new Director(request.getDirector().getName())
                    ));
            film.setDirector(director);
        }

        // ✅ Actors：直接用你已有的 Actor 对象，但做防重复入库
        if (request.getActors() != null) {
            Set<Actor> actors = request.getActors()
                    .stream()
                    .map(actor -> actorRepository
                            .findByName(actor.getName())
                            .orElseGet(() -> actorRepository.save(
                                    new Actor(actor.getName())
                            ))
                    )
                    .collect(Collectors.toSet());

            film.setActors(actors);
        }

        // ✅ 图片处理
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            film.setCoverImageUrl(imageUrl);
        }

        Film updated = filmRepository.save(film);

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

    private String saveImage(MultipartFile file) {
        try {
            String uploadDir = "uploads/films/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }


    private Director findOrCreateDirector(String name) {
        if (name == null || name.trim().isEmpty()) return null;

        String trimmed = name.trim();

        return directorRepository.findByNameIgnoreCase(trimmed)
                .orElseGet(() -> {
                    Director director = new Director();
                    director.setName(trimmed);
                    return directorRepository.save(director);
                });
    }

    private Set<Actor> findOrCreateActors(Set<String> names) {
        Set<Actor> actors = new LinkedHashSet<>();
        if (names == null || names.isEmpty()) return actors;

        for (String raw : names) {
            if (raw == null) continue;

            String name = raw.trim();
            if (name.isEmpty()) continue;

            Actor actor = actorRepository.findByNameIgnoreCase(name)
                    .orElseGet(() -> {
                        Actor a = new Actor();
                        a.setName(name);
                        return actorRepository.save(a);
                    });

            actors.add(actor);
        }
        return actors;
    }




}
