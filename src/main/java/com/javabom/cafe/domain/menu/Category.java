package com.javabom.cafe.domain.menu;

public enum Category {
    CAKE("케이크"),
    BEVERAGE("음료");

    private final String name;

    Category(final String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return (long) this.ordinal();
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
