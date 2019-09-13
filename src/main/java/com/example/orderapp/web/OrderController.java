package com.example.orderapp.web;

import com.example.orderapp.api.CreateOrderDto;
import com.example.orderapp.api.ModifyOrderDto;
import com.example.orderapp.api.OrderApplicationService;
import com.example.orderapp.api.OrderDto;
import com.example.orderapp.core.order.OrderId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public OrderId create(@RequestBody CreateOrderDto createOrderDto) {
        return this.orderApplicationService.create(createOrderDto);
    }

    @PutMapping("/{orderId}")
    public OrderDto modify(@PathVariable OrderId orderId, @RequestBody ModifyOrderDto modifyOrderDto) {
        return this.orderApplicationService.modify(orderId, modifyOrderDto);
    }

    @GetMapping("/{orderId}/start-topping")
    public void startTopping(@PathVariable OrderId orderId) {
        this.orderApplicationService.startTopping(orderId);
    }

    @GetMapping
    public List<OrderDto> list() {
        return this.orderApplicationService.findAll();
    }
}
