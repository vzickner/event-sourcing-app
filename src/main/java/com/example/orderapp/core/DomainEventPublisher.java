package com.example.orderapp.core;

public interface DomainEventPublisher {

    void publish(DomainEvent domainEvent);

}
