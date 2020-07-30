package com.javabom.cafe.controller.dto;

public class NewOrderDto {

    private Long tableId;

    private Long orderMenuId;

    private int orderAmount;

    public NewOrderDto() {
    }

    public NewOrderDto(final Long tableId, final Long orderMenuId, final int orderAmount) {
        this.tableId = tableId;
        this.orderMenuId = orderMenuId;
        this.orderAmount = orderAmount;
    }

    public Long getTableId() {
        return tableId;
    }

    public Long getOrderMenuId() {
        return orderMenuId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}
