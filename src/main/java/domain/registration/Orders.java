package domain.registration;

import domain.menu.OrderMenu;
import domain.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Table table, final OrderMenu orderMenu) {
        Order order = Order.of(table, orderMenu);

        if (!contains(order)) {
            Order previousOrderTable = findOrder(order);
            previousOrderTable.addMenu(orderMenu);
        }

        this.orders.add(order);
    }

    private boolean contains(final Order newOrder) {
        return orders.stream()
                .anyMatch(order -> order.isSameTable(newOrder));
    }

    public Order findOrder(final Order order) {
        return orders.stream()
                .filter(previousOrder -> previousOrder.isSameTable(order))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(("주문한 적이 없습니다.")));
    }


    public Order findByTableNumber(final int tableNumber) {
        return orders.stream()
                .filter(order -> order.isSameTableNumber(tableNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("입력한 테이블 번호 : %d, 해당 테이블은 존재하지 않습니다.", tableNumber)));
    }

    public void remove(final Order order) {
        orders.remove(order);
    }
}
