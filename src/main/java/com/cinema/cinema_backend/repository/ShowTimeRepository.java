package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findAllByFilmId(Long filmId);
}
