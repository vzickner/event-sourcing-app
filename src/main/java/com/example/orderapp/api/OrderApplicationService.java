package com.example.orderapp.api;

import com.example.orderapp.core.order.Order;
import com.example.orderapp.core.order.OrderId;
import com.example.orderapp.core.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {

    private OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderId create(CreateOrderDto createOrder) {
        return new Order.Builder()
                .setOrderId(new OrderId())
                .setName(createOrder.getName())
                .setCondiments(createOrder.getCondiments())
                .build()
                .getOrderId();
    }

    public List<OrderDto> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderAssembler::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto modify(OrderId orderId, ModifyOrderDto modifyOrderDto) {
        Order order = this.orderRepository.findById(orderId);
        order.changeCondiments(modifyOrderDto.getCondiments());
        return OrderAssembler.toOrderDto(order);
    }

    public void startTopping(OrderId orderId) {
        Order order = this.orderRepository.findById(orderId);
        order.startTopping();
    }
}
