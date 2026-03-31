package com.glauber.order.interfaces.rest;

import com.glauber.order.application.dto.CreateOrderRequest;
import com.glauber.order.application.usecases.CreateOrderUseCase;
import com.glauber.order.domain.entities.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return createOrderUseCase.execute(request);
    }
}