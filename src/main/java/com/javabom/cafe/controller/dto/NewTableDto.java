package com.javabom.cafe.controller.dto;

public class NewTableDto {

    private String tableName;

    public NewTableDto() {
    }

    public NewTableDto(final String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
