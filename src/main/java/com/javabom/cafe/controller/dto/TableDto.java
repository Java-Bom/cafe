package com.javabom.cafe.controller.dto;

public class TableDto {

    private Long tableId;

    private String tableName;

    private String orderStatus;

    public TableDto() {
    }

    public TableDto(final Long id, final String tableName, final String orderStatus) {
        this.tableId = id;
        this.tableName = tableName;
        this.orderStatus = orderStatus;
    }

    public Long getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
