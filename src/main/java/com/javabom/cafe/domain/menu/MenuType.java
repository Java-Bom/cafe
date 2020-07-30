package com.javabom.cafe.domain.menu;

import java.util.Arrays;

public enum MenuType {
    CAKE("케이크", 1),
    BEVERAGE("음료", 2);

    private final java.lang.String name;
    private final int menuTypeId;

    MenuType(final java.lang.String name, final int menuTypeId) {
        this.name = name;
        this.menuTypeId = menuTypeId;
    }

    public static MenuType findByName(final java.lang.String typeName) {
        return Arrays.stream(values())
                .filter(category -> category.name().equals(typeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        java.lang.String.format("입력한 메뉴 타입 : %s - 입력한 메뉴 타입이 존재하지 않습니다.", typeName)));

    }

    public java.lang.String getName() {
        return name;
    }

    public int getMenuTypeId() {
        return menuTypeId;
    }

    @Override


    public java.lang.String toString() {
        return "[" + name + "]";
    }
}
