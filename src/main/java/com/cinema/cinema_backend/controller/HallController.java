package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.HallDto;
import com.cinema.cinema_backend.dto.HallUpdateRequest;
import com.cinema.cinema_backend.dto.mapper.HallWrapper;
import com.cinema.cinema_backend.model.Hall;
import com.cinema.cinema_backend.service.HallService;
import com.cinema.cinema_backend.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;
    private final SeatService seatService;

    public HallController(HallService hallService, SeatService seatService) {
        this.hallService = hallService;
        this.seatService = seatService;
    }

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<HallDto>> createHall(@RequestBody Hall hall) {
        Hall saved = hallService.save(hall);
        seatService.createSeatsForHall(saved, saved.getCapacity());
        HallDto dto = HallWrapper.toDto(saved);
        ApiResponse<HallDto> response = new ApiResponse<>(dto, "Hall created", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Read all
    @GetMapping
    public ResponseEntity<ApiResponse<List<HallDto>>> getAllHalls() {
        List<HallDto> allHalls = hallService.findAll()
                .stream()
                .map(HallWrapper::toDto)
                .collect(Collectors.toList());

        ApiResponse<List<HallDto>> response =
                new ApiResponse<>(allHalls, "All halls fetched", null);

        return ResponseEntity.ok(response);
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HallDto>> getHallById(@PathVariable Long id) {
        return hallService.findById(id)
                .map(hall -> {
                    HallDto dto = HallWrapper.toDto(hall);
                    ApiResponse<HallDto> response =
                            new ApiResponse<>(dto, "Hall fetched", null);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(null, null, "Hall not found"))
                );
    }

    // Partial update
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<HallDto>> updateHall(
            @PathVariable Long id,
            @RequestBody HallUpdateRequest request
    ) {
        try {
            Hall updated = hallService.updateHall(id, request);
            HallDto dto = HallWrapper.toDto(updated);

            ApiResponse<HallDto> response =
                    new ApiResponse<>(dto, "Hall updated", null);

            return ResponseEntity.ok(response);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, null, "Hall not found"));
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHall(@PathVariable Long id) {
        boolean deleted = hallService.deleteById(id);

        if (deleted) {
            ApiResponse<Void> response =
                    new ApiResponse<>(null, "Hall deleted", null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Void> response =
                    new ApiResponse<>(null, null, "Hall not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
