package com.javabom.cafe.domain.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum MenuType {
    CAKE(1, "케이크"),
    BEVERAGE(2, "음료"),
    EMPTY(-1, "");

    private final int id;
    private final String name;

    MenuType(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static MenuType findByName(String name) {
        return Arrays.stream(values())
                .filter(category -> category.equalsOf(name))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("이름: %s, 존재하지 않는 이름입니다.", name)));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private boolean equalsOf(final String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
