package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ShowTimeUpdateRequest;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.model.ShowTime;
import com.cinema.cinema_backend.service.ShowTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowTimeController {

    private ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService){
        this.showTimeService = showTimeService;
    }

    @PostMapping
    public ResponseEntity<ShowTime> createShowTime(@RequestBody ShowTime showTime) {
        ShowTime saved = showTimeService.save(showTime);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ShowTime>> getAllShowTimes() {
        List<ShowTime> list = showTimeService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTime> getShowTimeById(@PathVariable Long id) {
        return showTimeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShowTime> updateShowTime(@PathVariable Long id,
                                                   @RequestBody ShowTimeUpdateRequest request) {
        ShowTime updated = showTimeService.updateShowTime(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowTime(@PathVariable Long id) {
        boolean deleted = showTimeService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
