package com.example.orderapp.core.order;

import com.example.orderapp.core.DomainEvent;

public abstract class OrderDomainEvent implements DomainEvent<Order.Builder> {

    private OrderId orderId;

    OrderDomainEvent() {
        // Jackson
    }

    OrderDomainEvent(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    @Override
    public String getId() {
        return orderId.getValue();
    }

}
