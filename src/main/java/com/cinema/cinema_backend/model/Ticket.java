package com.cinema.cinema_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private ShowTime showTime; // 绑定场次，而不是电影

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // 票绑定的座位

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // 订单，可为空

    private BigDecimal price;

    private boolean isAvailable = true;

    public Ticket() {}

    public Ticket(Long id, ShowTime showTime, Seat seat, Order order, BigDecimal price, boolean isAvailable) {
        this.id = id;
        this.showTime = showTime;
        this.seat = seat;
        this.order = order;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // ----------------- Getter & Setter -----------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ShowTime getShowTime() { return showTime; }
    public void setShowTime(ShowTime showTime) { this.showTime = showTime; }

    public Seat getSeat() { return seat; }
    public void setSeat(Seat seat) { this.seat = seat; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
