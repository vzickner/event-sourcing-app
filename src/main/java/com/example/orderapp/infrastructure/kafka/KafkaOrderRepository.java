package com.example.orderapp.infrastructure.kafka;

import com.example.orderapp.core.DomainEvent;
import com.example.orderapp.core.order.Order;
import com.example.orderapp.core.order.OrderDomainEvent;
import com.example.orderapp.core.order.OrderId;
import com.example.orderapp.core.order.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class KafkaOrderRepository implements OrderRepository {

    private final Map<OrderId, Order.Builder> orders = new HashMap<>();
    private final ObjectMapper objectMapper;

    public KafkaOrderRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "orders")
    public void listen(String event) throws IOException {
        DomainEvent domainEvent = this.objectMapper.readValue(event, DomainEvent.class);
        if (!(domainEvent instanceof OrderDomainEvent)) {
            return;
        }
        OrderDomainEvent orderDomainEvent = (OrderDomainEvent) domainEvent;

        OrderId orderId = orderDomainEvent.getOrderId();
        Order.Builder builder = orders.getOrDefault(orderId, new Order.Builder());
        orders.put(orderId, orderDomainEvent.apply(builder));
    }

    @Override
    public Order findById(OrderId orderId) {
        Order.Builder builder = orders.get(orderId);
        if (builder == null) {
            return null;
        }
        return builder.buildWithoutEvent();
    }

    @Override
    public List<Order> findAll() {
        return orders
                .values()
                .stream()
                .map(Order.Builder::buildWithoutEvent)
                .collect(Collectors.toList());
    }
}
