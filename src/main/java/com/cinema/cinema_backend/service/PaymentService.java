package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.PaymentUpdateRequest;
import com.cinema.cinema_backend.model.Payment;
import com.cinema.cinema_backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Read all
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    // Read by id
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    // Partial update
    public Payment updatePayment(Long id, PaymentUpdateRequest request) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));

        request.getStatus().ifPresent(existing::setStatus);

        return paymentRepository.save(existing);
    }

    // Delete
    public boolean deleteById(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

