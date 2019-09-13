package com.example.orderapp.core.order;

import java.util.List;

public interface OrderRepository {

    Order findById(OrderId orderId);

    List<Order> findAll();

}
