package domain.order;

import domain.enums.PayType;
import domain.table.Table;
import domain.vo.Money;

import java.util.List;

public class OrderTable {
    private final Orders orders;
    private final Table table;

    public OrderTable(Table table) {
        this.orders = new Orders();
        this.table = table;
    }

    public void addOrder(final Order order) {
        this.orders.add(order);
    }

    public boolean equalsBy(final Table table) {
        return this.table.equalsOf(table);
    }

    public Money calculateFee(final int paymentMethod) {
        checkOrderEmpty();
        return PayType.findByType(paymentMethod)
                .calculateDiscountFee(orders);
    }

    private void checkOrderEmpty() {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(String.format("테이블 번호: %s, 주문한 기록이 없습니다.", table.toString()));
        }
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public List<Order> getOrders() {
        return this.orders.get();
    }
}
