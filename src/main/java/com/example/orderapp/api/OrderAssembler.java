package com.example.orderapp.api;

import com.example.orderapp.core.order.Order;

public class OrderAssembler {
    public static OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getName(),
                order.getCondiments(),
                order.getStatus()
        );
    }
}
