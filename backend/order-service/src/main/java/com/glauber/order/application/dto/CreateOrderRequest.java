package com.glauber.order.application.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String customerId,
        BigDecimal totalAmount
) {}