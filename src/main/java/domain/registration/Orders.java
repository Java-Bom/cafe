package domain.registration;

import domain.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order) {
        orders.add(order);
    }

    public Order getOrderByTable(final Table table) {
        return orders.stream()
                .filter(order -> order.isSameByTable(table))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("선택한 테이블 : %d - 해당 테이블은 주문한적이 없습니다.", table.getNumber())));
    }

    public void remove(final Order orderByTable) {
        orders.remove(orderByTable);
    }
}
