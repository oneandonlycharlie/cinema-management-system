package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.SeatDto;
import com.cinema.cinema_backend.dto.mapper.SeatWrapper;
import com.cinema.cinema_backend.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // Read all
    @GetMapping
    public ResponseEntity<ApiResponse<List<SeatDto>>> getAllSeats() {

        List<SeatDto> dtos = seatService.findAll()
                .stream()
                .map(SeatWrapper::toDto)
                .toList();

        return ResponseEntity.ok(new ApiResponse<>(dtos, "All seats fetched", null));
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SeatDto>> getSeatById(@PathVariable Long id) {

        return seatService.findById(id)
                .map(seat -> ResponseEntity.ok(
                        new ApiResponse<>(SeatWrapper.toDto(seat), "Seat fetched", null)
                ))
                .orElseGet(() ->
                        ResponseEntity.status(404).body(
                                new ApiResponse<>(null, null, "Seat not found")
                        )
                );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSeat(@PathVariable Long id) {
        boolean deleted = seatService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(null, "Seat deleted", null));
        }

        return ResponseEntity.status(404).body(new ApiResponse<>(null, null, "Seat not found"));
    }
}
