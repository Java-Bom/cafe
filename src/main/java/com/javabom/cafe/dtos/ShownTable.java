package com.javabom.cafe.dtos;

public class ShownTable {
    private final int id;
    private final String name;
    private final Boolean hasMenu;

    public ShownTable(int id, String name, Boolean hasMenu) {
        this.id = id;
        this.name = name;
        this.hasMenu = hasMenu;
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
