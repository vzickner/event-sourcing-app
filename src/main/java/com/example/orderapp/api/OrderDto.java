package com.example.orderapp.api;

import com.example.orderapp.core.condiment.CondimentId;
import com.example.orderapp.core.order.OrderId;
import com.example.orderapp.core.order.OrderStatus;

import java.util.List;

public class OrderDto {

    private OrderId orderId;
    private String name;
    private List<CondimentId> condiments;
    private OrderStatus status;

    public OrderDto(OrderId orderId, String name, List<CondimentId> condiments, OrderStatus status) {
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
}
