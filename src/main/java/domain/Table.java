package domain;

import java.util.List;

public class Table {
    private final int number;
    private final String name;
    private OrderMenus orderMenus = OrderMenus.empty();

    public Table(final int number, final String name) {
        this.number = number;
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
