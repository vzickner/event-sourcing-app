package com.example.orderapp.core.condiment;

import java.util.Objects;
import java.util.UUID;

public class CondimentId {

    private String value;

    public CondimentId() {
        this(UUID.randomUUID().toString());
    }

    public CondimentId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CondimentId that = (CondimentId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CondimentId{" +
                "value='" + value + '\'' +
                '}';
    }
}
