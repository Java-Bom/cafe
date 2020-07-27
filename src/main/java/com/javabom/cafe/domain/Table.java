package com.javabom.cafe.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;

    private String name;

    @OneToOne
    private OrderMenus orderMenus = OrderMenus.empty();

    public Table() {
    }

    public Table(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void addMenu(Menu menu, int quantity) {
        this.orderMenus.addMenu(menu, quantity);
    }

    public boolean hasMenu() {
        return orderMenus.isNotEmpty();
    }

    public List<OrderDetail> getOrderDetails() {
        return orderMenus.getOrderDetails();
    }

    public void clear() {
        this.orderMenus = OrderMenus.empty();
    }

    public int getTotalPrice(Payment payment) {
        return this.orderMenus.getTotalPrice(payment);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
