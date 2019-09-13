package com.example.orderapp.core.order;

import com.example.orderapp.core.condiment.CondimentId;

import java.util.List;

public class OrderCreatedDomainEvent extends OrderDomainEvent {

    private String name;
    private List<CondimentId> condiments;

    private OrderCreatedDomainEvent() {
    }

    public OrderCreatedDomainEvent(OrderId orderId, String name, List<CondimentId> condiments) {
        super(orderId);
        this.name = name;
        this.condiments = condiments;
    }

    public String getName() {
        return name;
    }

    public List<CondimentId> getCondiments() {
        return condiments;
    }

    @Override
    public Order.Builder apply(Order.Builder builder) {
        return builder
                .setName(name)
                .setOrderId(getOrderId())
                .setCondiments(condiments)
                .setStatus(OrderStatus.WAITING);
    }
}
