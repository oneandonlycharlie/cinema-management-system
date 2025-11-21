package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.TicketDto;
import com.cinema.cinema_backend.dto.TicketUpdateRequest;
import com.cinema.cinema_backend.dto.mapper.TicketMapper;
import com.cinema.cinema_backend.model.Ticket;
import com.cinema.cinema_backend.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketDto>>> getAllTickets() {
        List<TicketDto> dtos = ticketService.findAll()
                .stream()
                .map(TicketMapper::toDto)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(dtos, "All tickets fetched", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> getTicketById(@PathVariable Long id) {
        return ticketService.findById(id)
                .map(ticket -> ResponseEntity.ok(new ApiResponse<>(TicketMapper.toDto(ticket), "Ticket fetched", null)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, null, "Ticket not found")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> updateTicket(
            @PathVariable Long id,
            @RequestBody TicketUpdateRequest request) {

        try {
            Ticket updated = ticketService.updateTicket(id, request);
            TicketDto dto = TicketMapper.toDto(updated);
            return ResponseEntity.ok(new ApiResponse<>(dto, "Ticket updated", null));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable Long id) {
        boolean deleted = ticketService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(null, "Ticket deleted", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, null, "Ticket not found"));
        }
    }
}
