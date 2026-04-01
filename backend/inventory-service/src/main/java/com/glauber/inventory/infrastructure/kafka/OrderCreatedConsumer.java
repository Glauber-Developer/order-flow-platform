package com.glauber.inventory.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "inventory-group"
    )
    public void consume(String event) {
        System.out.println("EVENTO RECEBIDO NO INVENTORY: " + event);
    }
}