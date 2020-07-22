package domain.order;

import domain.enums.Category;
import domain.vo.Money;
import domain.vo.Quantity;

import java.util.*;

public class Orders {
    private final List<Order> orders;

    public Orders() {
        orders = new ArrayList<>();
    }

    public void add(final Order order) {
        if (orders.contains(order)) {
            Order curOrder = findEqualBy(order);
            curOrder.addQuantity(order);
        }

        if (!orders.contains(order)) {
            this.orders.add(order);
        }
    }

    private Order findEqualBy(final Order order) {
        return orders.stream()
                .filter(thisOrder -> thisOrder.equals(order))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 주문입니다."));
    }

    public Money getAmount() {
        int amount = 0;
        for (Order order : orders) {
            amount += order.getAmount();
        }

        return Money.of(amount);
    }

    public Quantity getQuantityBy(final Category category) {
        int allQuantity = 0;
        for (Order order : orders) {
            allQuantity += order.getQuantityBy(category);
        }

        return allQuantity == 0 ? Quantity.ZERO : Quantity.of(allQuantity);
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public List<Order> get() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        final Orders orders1 = (Orders) o;
        return Objects.equals(orders, orders1.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
