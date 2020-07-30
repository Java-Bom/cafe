package com.javabom.cafe.controller.dto;

public class NewMenuDto {

    private String menuType;

    private String menuName;

    private double menuPrice;

    public NewMenuDto() {
    }

    public NewMenuDto(final String menuType, final String menuName, final double menuPrice) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public String getMenuType() {
        return menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getMenuPrice() {
        return menuPrice;
    }
}
