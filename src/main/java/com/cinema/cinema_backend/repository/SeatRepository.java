package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByHallIdOrderByIdDesc(Long hallId);
}