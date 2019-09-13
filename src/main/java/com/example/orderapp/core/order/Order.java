package com.example.orderapp.core.order;

import com.example.orderapp.core.DomainEventPublisherRegistry;
import com.example.orderapp.core.condiment.CondimentId;

import java.util.List;

public class Order {

    private OrderId orderId;

    private String name;

    private List<CondimentId> condiments;

    private OrderStatus status;

    private Order() {
        // Jackson
    }

    public Order(OrderId orderId, String name, List<CondimentId> condiments, OrderStatus status) {
        this.orderId = orderId;
        this.name = name;
        this.condiments = condiments;
        this.status = status;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public List<CondimentId> getCondiments() {
        return condiments;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void startTopping() {
        this.status = OrderStatus.TOPPING;
        DomainEventPublisherRegistry
                .getInstance()
                .publish(new OrderStartedToppingEvent(orderId));
    }

    public void changeCondiments(List<CondimentId> condiments) {
        this.condiments = condiments;
        DomainEventPublisherRegistry
                .getInstance()
                .publish(new ModifyOrderDomainEvent(orderId, condiments));

    }

    public static class Builder {

        private OrderId orderId;
        private String name;
        private List<CondimentId> condiments;
        private OrderStatus status = OrderStatus.WAITING;

        public Builder setOrderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCondiments(List<CondimentId> condiments) {
            this.condiments = condiments;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order build() {
            Order order = buildWithoutEvent();
            DomainEventPublisherRegistry.getInstance()
                    .publish(new OrderCreatedDomainEvent(orderId, name, condiments));
            return order;
        }

        public Order buildWithoutEvent() {
            return new Order(orderId, name, condiments, status);
        }
    }

}
