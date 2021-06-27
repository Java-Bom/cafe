package repository;

import domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final int MAX_MENU_PER_TABLE = 30;
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order)
    {
        orders.add(order);
    }

}
