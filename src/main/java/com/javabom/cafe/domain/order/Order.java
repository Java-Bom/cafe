package com.javabom.cafe.domain.order;

import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.vo.Quantity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "ORDERS")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_ID")
    private Table table;

    @Embedded
    private Quantity quantity;

    @Builder
    public Order(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void addQuantity(final Order order) {
        this.quantity = this.quantity.plus(order.quantity);
    }

    public int getAmount() {
        return menu.getMultiplyPriceOf(quantity.get());
    }

    public int getQuantityBy(final MenuType menuType) {
        if (menu.equalsOf(menuType)) {
            return quantity.get();
        }
        return 0;
    }

    public int getQuantityBy(final String name) {
        if (menu.equalsOf(name)) {
            return quantity.get();
        }

        return 0;
    }
}
