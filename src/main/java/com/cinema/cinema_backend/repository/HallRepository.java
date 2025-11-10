package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    // JpaRepository 已经提供基础 CRUD
}
