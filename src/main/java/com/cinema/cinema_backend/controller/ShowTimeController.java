package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.ShowTimeDto;
import com.cinema.cinema_backend.dto.ShowTimeCreateRequest;
import com.cinema.cinema_backend.dto.ShowTimeUpdateRequest;
import com.cinema.cinema_backend.dto.mapper.ShowTimeMapper;
import com.cinema.cinema_backend.model.ShowTime;
import com.cinema.cinema_backend.service.ShowTimeService;
import org.apache.el.lang.ELArithmetic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films/{filmId}/showtimes")
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ShowTimeDto>> createShowTime(@PathVariable Long filmId, @RequestBody ShowTimeCreateRequest request) {
        ShowTime saved = showTimeService.createShowTime(filmId, request);
        ShowTimeDto dto = ShowTimeMapper.toDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(dto, "ShowTime created", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShowTimeDto>>> getAllShowTimes(@PathVariable Long filmId) {
        List<ShowTimeDto> dtos = showTimeService.findByFilmId(filmId)
                .stream()
                .map(ShowTimeMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(dtos, "All showtimes fetched", null));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowTimeDto>> getShowTimeById(@PathVariable Long id) {
        return showTimeService.findById(id)
                .map(showTime -> ResponseEntity.ok(
                        new ApiResponse<>(ShowTimeMapper.toDto(showTime), "ShowTime fetched", null)
                ))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(null, null, "ShowTime not found"))
                );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ShowTimeDto>> updateShowTime(
            @PathVariable Long id,
            @RequestBody ShowTimeUpdateRequest request
    ) {
        try {
            ShowTime updated = showTimeService.updateShowTime(id, request);
            ShowTimeDto dto = ShowTimeMapper.toDto(updated);

            return ResponseEntity.ok(new ApiResponse<>(dto, "ShowTime updated", null));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShowTime(@PathVariable Long id) {
        boolean deleted = showTimeService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(null, "ShowTime deleted", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, null, "ShowTime not found"));
        }
    }
}
