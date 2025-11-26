package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByName(String name);

    Optional<Actor> findByNameIgnoreCase(String name);
}
