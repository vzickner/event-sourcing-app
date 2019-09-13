package com.example.orderapp.api;

import com.example.orderapp.core.condiment.CondimentId;

import java.util.List;

public class ModifyOrderDto {

    private List<CondimentId> condiments;

    public List<CondimentId> getCondiments() {
        return condiments;
    }
}
