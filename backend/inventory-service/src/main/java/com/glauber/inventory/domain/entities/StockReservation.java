package com.glauber.inventory.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_reservations")
public class StockReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private String status;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    protected StockReservation() {}

    public StockReservation(UUID orderId) {
        this.orderId = orderId;
        this.status = "RESERVED";
        this.reservedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }
}