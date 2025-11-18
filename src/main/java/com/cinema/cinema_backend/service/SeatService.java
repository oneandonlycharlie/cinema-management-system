package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.model.Hall;
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

    public void createSeatsForHall(Hall hall, int amountToAdd){
        for ( int i = 0; i < amountToAdd; i++){
            Seat seat = new Seat();
            seat.setHall(hall);
            seatRepository.save(seat);
            System.out.println("new seat added!" + seat);
        }
    }

    public void deleteSeatsForHall(Hall hall, int amountToDelete) {
        List<Seat> seats = seatRepository.findByHallIdOrderByIdDesc(hall.getId());
        for (int i = 0; i < amountToDelete && i < seats.size(); i++) {
            Seat seat = seats.get(i);
            if (!seat.getTickets().isEmpty()) continue;
            seatRepository.delete(seat);
            System.out.println("seat deleted");
        }

    }
}

