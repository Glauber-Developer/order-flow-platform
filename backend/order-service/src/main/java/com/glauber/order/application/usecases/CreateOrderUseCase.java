package com.glauber.order.application.usecases;

import com.glauber.order.application.dto.CreateOrderRequest;
import com.glauber.order.domain.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    public Order execute(CreateOrderRequest request) {
        return new Order(request.customerId(), request.totalAmount());
    }
}