package domain.registration;

import domain.menu.OrderMenu;
import domain.menu.OrderMenus;
import domain.table.Table;

import java.util.List;

public class Order {

    private final OrderMenus orderMenus;

    private final Table table;

    private Order(final Table table) {
        orderMenus = new OrderMenus();
        this.table = table;
    }

    public static Order initOrder(final Table table) {
        return new Order(table);
    }

    public void addMenu(final OrderMenu order) {
        orderMenus.addMenu(order);
    }

    public boolean isSameByTable(final Table table) {
        return table.equals(this.table);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus.getOrderMenus();
    }
}