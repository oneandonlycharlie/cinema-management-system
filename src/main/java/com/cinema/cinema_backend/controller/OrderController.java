package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.ApiResponse;
import com.cinema.cinema_backend.dto.OrderCreateRequest;
import com.cinema.cinema_backend.dto.OrderDto;
import com.cinema.cinema_backend.dto.OrderUpdateRequest;
import com.cinema.cinema_backend.model.Order;
import com.cinema.cinema_backend.security.CinemaUserDetails;
import com.cinema.cinema_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<OrderDto>> createOrder(
            @RequestBody OrderCreateRequest request,
            @AuthenticationPrincipal CinemaUserDetails userDetails // 假设你已经把用户ID存到SecurityContext
    ) {
        try {
            OrderDto dto = orderService.createOrder(request, userDetails.getCinemaUser().getId());
            return ResponseEntity.ok(new ApiResponse<>(dto, null, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, e.getMessage(), null));
        }
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Partial update
    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequest request) {
        Order updated = orderService.updateOrder(id, request);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<ApiResponse<OrderDto>> payOrder(
            @PathVariable Long orderId,
            @AuthenticationPrincipal CinemaUserDetails userDetails) {
        Long userId = userDetails.getCinemaUser().getId();
        OrderDto dto = orderService.payOrder(userId, orderId);
        ApiResponse<OrderDto> response = new ApiResponse<>(dto, null, null);
        return ResponseEntity.ok(response);
    }
}

