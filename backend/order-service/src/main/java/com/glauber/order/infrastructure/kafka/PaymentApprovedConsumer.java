package com.glauber.order.infrastructure.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.order.infrastructure.persistence.SpringDataOrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentApprovedConsumer {

    private final SpringDataOrderRepository repository;
    private final ObjectMapper objectMapper;

    public PaymentApprovedConsumer(SpringDataOrderRepository repository,
                                   ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payment-approved", groupId = "order-group")
    public void consume(String event) throws Exception {
        JsonNode json = objectMapper.readTree(event);
        UUID orderId = UUID.fromString(json.get("orderId").asText());

        var order = repository.findById(orderId).orElseThrow();
        order.setStatus("CONFIRMED");
        repository.save(order);

        System.out.println("PEDIDO CONFIRMADO: " + orderId);
    }
}