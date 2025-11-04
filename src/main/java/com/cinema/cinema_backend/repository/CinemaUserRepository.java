package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.CinemaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUser, Long> {
    Optional<CinemaUser> findUserByName(String name);

    Optional<CinemaUser> findUserByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);


}
