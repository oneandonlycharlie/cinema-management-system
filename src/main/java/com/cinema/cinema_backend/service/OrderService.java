package com.cinema.cinema_backend.service;


import com.cinema.cinema_backend.dto.OrderUpdateRequest;
import com.cinema.cinema_backend.model.Order;
import com.cinema.cinema_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // Read all
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    // Read by id
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    // Partial update
    public Order updateOrder(Long id, OrderUpdateRequest request) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));

        request.getStatus().ifPresent(existing::setStatus);

        return orderRepository.save(existing);
    }

    // Delete
    public boolean deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

