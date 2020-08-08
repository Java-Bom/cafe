package com.javabom.cafe.dto.menu;

public class MenuFindAllTypeResDto {

    private int menuTypeId;
    private String menuType;

    public MenuFindAllTypeResDto(final int menuTypeId, final String menuType) {
        this.menuTypeId = menuTypeId;
        this.menuType = menuType;
    }
}
