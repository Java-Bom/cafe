package com.javabom.cafe.dto.menu;

import com.javabom.cafe.domain.menu.MenuType;

public class MenuFindAllResDto {

    private final Long id;
    private final String name;
    private final int price;
    private final MenuType menuType;

    public MenuFindAllResDto(final Long id, final String name, final int price, final MenuType menuType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }
}
