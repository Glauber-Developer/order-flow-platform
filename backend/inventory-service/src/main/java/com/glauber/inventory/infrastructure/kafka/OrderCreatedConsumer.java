package com.glauber.inventory.infrastructure.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import com.glauber.inventory.domain.entities.StockReservation;
import com.glauber.inventory.infrastructure.persistence.StockReservationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCreatedConsumer {

    private final StockReservationRepository repository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderCreatedConsumer(StockReservationRepository repository,
                                ObjectMapper objectMapper,
                                KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(String event) throws Exception {
        JsonNode json = objectMapper.readTree(event);
        UUID orderId = UUID.fromString(json.get("orderId").asText());

        StockReservation reservation = new StockReservation(orderId);
        repository.save(reservation);
        kafkaTemplate.send("stock-reserved", event);

        System.out.println("ESTOQUE RESERVADO PARA PEDIDO: " + orderId);
    }
}