package com.javabom.cafe.controller.dto;

import java.util.List;

public class OrdersDto {

    private Long tableId;

    private String tableName;

    private List<OrderMenuDto> orders;

    public OrdersDto() {
    }

    public OrdersDto(final Long tableId, final String tableName, final List<OrderMenuDto> orders) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.orders = orders;
    }

    public Long getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public List<OrderMenuDto> getOrders() {
        return orders;
    }

}
