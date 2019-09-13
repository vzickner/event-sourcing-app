package com.example.orderapp.api;

import com.example.orderapp.core.condiment.CondimentId;

import java.util.List;

public class CreateOrderDto {

    private String name;
    private List<CondimentId> condiments;

    public CreateOrderDto(String name, List<CondimentId> condiments) {
        this.name = name;
        this.condiments = condiments;
    }

    public String getName() {
        return name;
    }

    public List<CondimentId> getCondiments() {
        return condiments;
    }
}
