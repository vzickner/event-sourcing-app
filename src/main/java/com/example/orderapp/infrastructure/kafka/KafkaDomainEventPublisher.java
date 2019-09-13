package com.example.orderapp.infrastructure.kafka;

import com.example.orderapp.core.DomainEvent;
import com.example.orderapp.core.DomainEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaDomainEventPublisher implements DomainEventPublisher {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public KafkaDomainEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        try {
            String event = this.objectMapper.writeValueAsString(domainEvent);
            this.kafkaTemplate.sendDefault(domainEvent.getId(), event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
