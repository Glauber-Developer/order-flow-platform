package com.glauber.order.application.usecases;

import com.glauber.order.application.dto.CreateOrderRequest;
import com.glauber.order.domain.entities.Order;
import com.glauber.order.domain.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(CreateOrderRequest request) {
        Order order = new Order(request.customerId(), request.totalAmount());
        return orderRepository.save(order);
    }
}