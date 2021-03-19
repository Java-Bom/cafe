package com.javabom.cafe.dtos;

public class CreateMenuDto {
    private final String menuName;
    private final int menuPrice;
    private final String menuType;

    public CreateMenuDto(String menuName, int menuPrice, String menuType) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public String getMenuType() {
        return menuType;
    }
}
