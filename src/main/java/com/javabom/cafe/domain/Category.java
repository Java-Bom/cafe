package com.javabom.cafe.domain;

public enum Category {
    CAKE("케이크"),
    BEVERAGE("음료");

    private final String name;

    Category(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
