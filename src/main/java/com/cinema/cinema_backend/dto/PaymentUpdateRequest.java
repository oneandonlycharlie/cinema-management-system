package com.cinema.cinema_backend.dto;
import com.cinema.cinema_backend.model.PaymentStatus;
import java.util.Optional;

    public class PaymentUpdateRequest {

        private Optional<PaymentStatus> status = Optional.empty();

        public PaymentUpdateRequest() {}


        public Optional<PaymentStatus> getStatus() {
            return status;
        }

        public void setStatus(Optional<PaymentStatus> status) {
            this.status = status;
        }

    }

