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
    private final SeatService seatService;

    public HallService(HallRepository hallRepository, SeatService seatService) {
        this.hallRepository = hallRepository;
        this.seatService = seatService;
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
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        int oldCapacity = hall.getCapacity();

        request.getName().ifPresent(hall::setName);
        request.getCapacity().ifPresent(newCapacity -> {
            hall.setCapacity(newCapacity);

            int diff = newCapacity - oldCapacity;

            if (diff > 0) {
                seatService.createSeatsForHall(hall, diff);
            } else if (diff < 0) {
                seatService.deleteSeatsForHall(hall, -diff);
            }
        });

        return hallRepository.save(hall);
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
