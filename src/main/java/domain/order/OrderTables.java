package domain.order;

import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderTables {

    private final List<OrderTable> orderTables;

    public OrderTables() {
        this.orderTables = new ArrayList<>();
    }

    public void addOrderTable(final Order order, final Table table) {
        OrderTable orderTable = getOrderTableBy(table);
        orderTable.addOrder(order);
        orderTables.add(orderTable);
    }

    public Money calculateFee(final int tableNumber, final int paymentMethod) {
        OrderTable orderTable = getOrderTableBy(TableRepository.find(tableNumber));
        checkTableEmpty(orderTable, tableNumber);
        return orderTable.calculateFee(paymentMethod);
    }

    private void checkTableEmpty(final OrderTable orderTable, final int tableNumber) {
        if (orderTable.isEmpty()) {
            throw new IllegalArgumentException(String.format("테이블 번호: %s, 주문한 기록이 없습니다.", tableNumber));
        }
    }

    public List<Order> findOrdersBy(final Table table) {
        OrderTable orderTable = getOrderTableBy(table);
        return orderTable.getOrders();
    }

    private OrderTable getOrderTableBy(final Table table) {
        return orderTables.stream()
                .filter(orderTable -> orderTable.equalsBy(table))
                .findFirst()
                .orElse(new OrderTable(table));
    }

    public void clearOf(final Table table) {
        OrderTable orderTable = getOrderTableBy(table);
        orderTables.remove(orderTable);
    }

    public List<OrderTable> get() {
        return Collections.unmodifiableList(orderTables);
    }
}
