package com.javabom.cafe.controller.dto;

public class OrderMenuDto {

    private String menuName;

    private int orderAmount;

    private double price;

    public OrderMenuDto() {
    }

    public OrderMenuDto(final String menuName, final int orderAmount, final double menuPrice) {
        this.menuName = menuName;
        this.orderAmount = orderAmount;
        this.price = menuPrice * orderAmount;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public double getPrice() {
        return price;
    }
}
