package com.javabom.cafe.domain;

import javax.persistence.*;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int price;

    public Menu() {
    }

    public Menu(final String name, final Category category, final int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getNumber() {
        return number;
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

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + price + "원";
    }
}
