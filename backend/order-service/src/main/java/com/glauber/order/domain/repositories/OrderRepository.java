package com.glauber.order.domain.repositories;

import com.glauber.order.domain.entities.Order;

public interface OrderRepository {
    Order save(Order order);
}
