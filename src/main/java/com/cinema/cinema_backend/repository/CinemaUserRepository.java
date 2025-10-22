package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.CinemaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaUserRepository extends JpaRepository<CinemaUser, Long> {
    Optional<CinemaUser> findUserByName(String name);

    boolean existsByUsername(String name);

}
