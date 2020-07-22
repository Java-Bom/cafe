package domain;

import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.OrderTables;
import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Money;
import domain.vo.Quantity;

import java.util.List;

public class POS {

    private final OrderTables orderTables;

    public POS() {
        this.orderTables = new OrderTables();
    }

    public void takeOrder(final int tableNumber, final int menuIdx, final int quantity) {
        Table table = TableRepository.find(tableNumber);
        Order order = new Order(MenuRepository.find(menuIdx), Quantity.of(quantity));
        orderTables.addOrderTable(order, table);
    }

    public Money calculateFee(final int tableNumber, final int paymentMethod) {
        return orderTables.calculateFee(tableNumber, paymentMethod);
    }

    public List<Order> findOrderHistoryOf(final int tableNumber) {
        Table table = TableRepository.find(tableNumber);
        return orderTables.findOrdersBy(table);
    }

    public void clearOf(final int tableNumber) {
        Table table = TableRepository.find(tableNumber);
        orderTables.clearOf(table);
    }
}
