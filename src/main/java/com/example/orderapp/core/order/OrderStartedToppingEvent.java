package com.example.orderapp.core.order;

public class OrderStartedToppingEvent extends OrderDomainEvent {

    private OrderStartedToppingEvent() {
    }

    public OrderStartedToppingEvent(OrderId orderId) {
        super(orderId);
    }

    @Override
    public Order.Builder apply(Order.Builder object) {
        return object
                .setStatus(OrderStatus.TOPPING);
    }
}
