package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.HallUpdateRequest;
import com.cinema.cinema_backend.model.Hall;
import com.cinema.cinema_backend.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    // Create
    public Hall save(Hall hall) {
        return hallRepository.save(hall);
    }

    // Read all
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    // Read by id
    public Optional<Hall> findById(Long id) {
        return hallRepository.findById(id);
    }

    // Partial update
    public Hall updateHall(Long id, HallUpdateRequest request) {
        Hall existing = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        request.getName().ifPresent(existing::setName);
        request.getCapacity().ifPresent(existing::setCapacity);

        return hallRepository.save(existing);
    }

    // Delete
    public boolean deleteById(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
