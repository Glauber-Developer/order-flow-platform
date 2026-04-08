package com.glauber.payment.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;

    protected Payment() {}

    public Payment(UUID orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = "APPROVED";
        this.processedAt = LocalDateTime.now();
    }
}