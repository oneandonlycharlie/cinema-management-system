package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findFilmByName(String name);

}
