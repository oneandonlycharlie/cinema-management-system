package com.cinema.cinema_backend.controller;

import com.cinema.cinema_backend.dto.PaymentUpdateRequest;
import com.cinema.cinema_backend.model.Payment;
import com.cinema.cinema_backend.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment saved = paymentService.save(payment);
        return ResponseEntity.ok(saved);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Partial update
    @PatchMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody PaymentUpdateRequest request) {
        Payment updated = paymentService.updatePayment(id, request);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        boolean deleted = paymentService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

