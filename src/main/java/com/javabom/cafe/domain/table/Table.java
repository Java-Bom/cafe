package com.javabom.cafe.domain.table;

import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.order.OrderStatus;
import com.javabom.cafe.domain.pay.PayType;
import com.javabom.cafe.domain.order.Order;
import com.javabom.cafe.domain.vo.Money;
import com.javabom.cafe.domain.vo.Quantity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "TABLES")
@Getter
@Setter
@NoArgsConstructor
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_ID")
    private Long id;

    @Column(name = "TABLE_NAME")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Table(final String name) {
        this.name = name;
    }

    public Money calculatePrice(final int type) {
        checkOrderEmpty();
        return PayType.findByType(type)
                .calculateDiscountFee(this);
    }

    private void checkOrderEmpty() {
        if (this.orders.isEmpty()) {
            throw new IllegalArgumentException(String.format("테이블 번호: %s, 주문한 기록이 없습니다.", this.id));
        }
    }

    public Money getAmount() {
        int amount = 0;
        for (Order order : orders) {
            amount += order.getAmount();
        }

        return Money.of(amount);
    }

    public Quantity getQuantityBy(final MenuType menuType) {
        int allQuantity = 0;
        for (Order order : orders) {
            allQuantity += order.getQuantityBy(menuType);
        }

        return allQuantity == 0 ? Quantity.ZERO : Quantity.of(allQuantity);
    }

    public void addOrder(final Order order) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        final Table table = (Table) o;
        return Objects.equals(getId(), table.getId()) &&
                Objects.equals(getName(), table.getName()) &&
                getStatus() == table.getStatus() &&
                Objects.equals(getOrders(), table.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getOrders());
    }
}
