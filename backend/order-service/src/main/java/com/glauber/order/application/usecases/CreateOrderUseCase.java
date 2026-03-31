package com.glauber.order.application.usecases;

import com.glauber.order.application.dto.CreateOrderRequest;
import com.glauber.order.domain.entities.Order;
import com.glauber.order.domain.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import com.glauber.order.application.dto.OrderCreatedEvent;
import com.glauber.order.infrastructure.kafka.OrderEventProducer;

@Service
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderEventProducer eventProducer;

    public CreateOrderUseCase(OrderRepository orderRepository, OrderEventProducer eventProducer) {
        this.orderRepository = orderRepository;
        this.eventProducer = eventProducer;
    }

    public Order execute(CreateOrderRequest request) {
        Order order = new Order(request.customerId(), request.totalAmount());
        Order saved = orderRepository.save(order);

        eventProducer.publish(
                new OrderCreatedEvent(
                        saved.getId(),
                        saved.getCustomerId(),
                        saved.getTotalAmount()
                )
        );

        return saved;
    }
}