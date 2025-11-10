package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.model.Seat;
import com.cinema.cinema_backend.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // Create
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    // Read all
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    // Read by id
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    // Delete
    public boolean deleteById(Long id) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

