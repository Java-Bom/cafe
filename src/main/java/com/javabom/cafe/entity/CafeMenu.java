package com.javabom.cafe.entity;

import domain.Category;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CafeMenu {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Enumerated
    private Category category;

    private int price;

    protected CafeMenu() {
    }

    public CafeMenu(final String name, final Category category, final int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
