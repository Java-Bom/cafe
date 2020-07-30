package com.javabom.cafe.dtos;

import domain.Table;

public class ShownTable {
    private final int id;
    private final String name;
    private final Boolean hasMenu;

    public ShownTable(Table table) {
        this.id = table.getNumber();
        this.name = table.getName();
        this.hasMenu = table.hasMenu();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getHasMenu() {
        return hasMenu;
    }
}
