package com.example.orderapp.infrastructure.kafka;

import com.example.orderapp.core.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaDomainEventListener {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public KafkaDomainEventListener(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @EventListener
    public void listen(DomainEvent domainEvent) throws JsonProcessingException {
        String event = this.objectMapper.writeValueAsString(domainEvent);
        this.kafkaTemplate.sendDefault(domainEvent.getId(), event);
    }

}
