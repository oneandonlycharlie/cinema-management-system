package com.cinema.cinema_backend.repository;

import com.cinema.cinema_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCinemaUserId(Long userId);
}
