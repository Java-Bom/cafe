package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order) {
        orders.add(order);
    }

    public List<Order> findByTable(final Table table) {
        return orders.stream()
                .filter(menu -> menu.isEqualTableNumberTo(table))
                .collect(Collectors.toList());
    }
}
