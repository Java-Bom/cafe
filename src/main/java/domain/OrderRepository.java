package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {
    private static final int MAX_ORDER_PER_TABLE = 30;
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order) {
        validate(order);
        orders.add(order);
    }

    private void validate(final Order order) {
        if (findByTable(order.getTableNumber()).size() >= MAX_ORDER_PER_TABLE) {
            throw new IllegalArgumentException(String.format("Max Order Counts Of Per Table is %d", MAX_ORDER_PER_TABLE));
        }
    }

    public List<Order> findByTable(final int tableNumber) {
        return orders.stream()
                .filter(menu -> menu.isEqualTableNumberTo(new Table(tableNumber)))
                .collect(Collectors.toList());
    }
}
