package com.glauber.inventory.infrastructure.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.inventory.domain.entities.StockReservation;
import com.glauber.inventory.infrastructure.persistence.StockReservationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCreatedConsumer {

    private final StockReservationRepository repository;
    private final ObjectMapper objectMapper;

    public OrderCreatedConsumer(StockReservationRepository repository,
                                ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(String event) throws Exception {
        JsonNode json = objectMapper.readTree(event);
        UUID orderId = UUID.fromString(json.get("orderId").asText());

        StockReservation reservation = new StockReservation(orderId);
        repository.save(reservation);

        System.out.println("ESTOQUE RESERVADO PARA PEDIDO: " + orderId);
    }
}