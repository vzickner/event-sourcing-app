package com.example.orderapp.core.order;

import com.example.orderapp.core.condiment.CondimentId;

import java.util.List;

public class ModifyOrderDomainEvent extends OrderDomainEvent {

    private List<CondimentId> condiments;

    private ModifyOrderDomainEvent() {
    }

    public ModifyOrderDomainEvent(OrderId orderId, List<CondimentId> condiments) {
        super(orderId);
        this.condiments = condiments;
    }

    @Override
    public Order.Builder apply(Order.Builder object) {
        return object
                .setCondiments(condiments);
    }
}
