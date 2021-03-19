package com.javabom.cafe.dtos;

import domain.Table;

public class ShownTable {
    private final int tableId;
    private final String tableName;
    private final Boolean hasMenu;

    public ShownTable(Table table) {
        this.tableId = table.getNumber();
        this.tableName = table.getName();
        this.hasMenu = table.hasMenu();
    }

    public int getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public Boolean getHasMenu() {
        return hasMenu;
    }
}
