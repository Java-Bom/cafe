package domain.registration;

import domain.menu.OrderMenu;
import domain.menu.OrderMenus;
import domain.table.Table;

import java.util.Objects;

public class Order {

    private final OrderMenus orderMenus;

    private final Table table;

    private Order(final OrderMenu orderMenu, final Table table) {
        orderMenus = new OrderMenus();
        orderMenus.addMenu(orderMenu);
        this.table = table;
    }

    public static Order of(final Table table, final OrderMenu orderMenu) {
        return new Order(orderMenu, table);
    }

    public void addMenu(final OrderMenu orderMenu) {
        orderMenus.addMenu(orderMenu);
    }

    public OrderMenus getOrderMenus() {
        return orderMenus;
    }

    public boolean isSameTableNumber(final int tableNumber) {
        return table.getNumber() == tableNumber;
    }

    public Boolean isSameTable(final Order newOrder) {
        return this.table.equals(newOrder.table);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return Objects.equals(getOrderMenus(), order.getOrderMenus()) &&
                Objects.equals(table, order.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderMenus(), table);
    }

}