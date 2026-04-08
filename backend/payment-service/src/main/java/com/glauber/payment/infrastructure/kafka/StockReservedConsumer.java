package com.glauber.payment.infrastructure.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import com.glauber.payment.domain.entities.Payment;
import com.glauber.payment.infrastructure.persistence.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class StockReservedConsumer {

    private final PaymentRepository repository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public StockReservedConsumer(PaymentRepository repository,
                                 ObjectMapper objectMapper,
                                 KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "stock-reserved", groupId = "payment-group")
    public void consume(String event) throws Exception {
        JsonNode json = objectMapper.readTree(event);

        UUID orderId = UUID.fromString(json.get("orderId").asText());
        BigDecimal amount = json.get("totalAmount").decimalValue();

        Payment payment = new Payment(orderId, amount);
        repository.save(payment);
        kafkaTemplate.send("payment-approved", event);

        System.out.println("PAGAMENTO APROVADO: " + orderId);
    }
}