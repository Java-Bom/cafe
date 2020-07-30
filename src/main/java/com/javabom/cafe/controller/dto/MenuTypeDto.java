package com.javabom.cafe.controller.dto;

public class MenuTypeDto {

    private int menuTypeId;

    private String menuType;

    public MenuTypeDto() {
    }

    public MenuTypeDto(final String menuType) {
        this.menuType = menuType;
    }

    public MenuTypeDto(final int menuTypeId, final String menuType) {
        this.menuTypeId = menuTypeId;
        this.menuType = menuType;
    }

    public String getMenuType() {
        return menuType;
    }

    public int getMenuTypeId() {
        return menuTypeId;
    }
}
