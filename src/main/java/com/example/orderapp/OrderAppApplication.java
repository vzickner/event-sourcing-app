package com.example.orderapp;

import com.example.orderapp.core.DomainEventPublisher;
import com.example.orderapp.core.DomainEventPublisherRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OrderAppApplication {

	@Autowired
	private DomainEventPublisher domainEventPublisher;

	public static void main(String[] args) {
		SpringApplication.run(OrderAppApplication.class, args);
	}

	@PostConstruct
	public void setDomainEventPublisher() {
		DomainEventPublisherRegistry.setInstance(domainEventPublisher);
	}
}
