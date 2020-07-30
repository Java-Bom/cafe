package com.javabom.cafe.domain.menu;

import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.domain.vo.Quantity;

import javax.persistence.*;

@Entity
public class OrderMenu {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private CafeTable table;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Embedded
    private Quantity quantity;

    private OrderMenu() {
    }

    private OrderMenu(final Menu menu, final CafeTable table, final int quantity) {
        this.menu = menu;
        this.table = table;
        this.quantity = Quantity.valueOf(quantity);
    }

    public static OrderMenu selectOrderMenu(final Menu menu, final CafeTable table, final int quantity) {
        OrderMenu orderMenu = new OrderMenu(menu, table, quantity);
        table.addMenu(orderMenu);
        return orderMenu;
    }

    public void extraOrder(final OrderMenu orderMenu) {
        this.quantity = quantity.addQuantity(orderMenu.quantity);
    }

    public Amount getTotalAmount() {
        return this.menu.getTotalAmount(quantity.getValue());
    }

    public boolean isSameCategory(MenuType menuType) {
        return menu.isSameCategory(menuType);
    }

    public boolean isSameMenu(final OrderMenu newOrderMenu) {
        return this.menu.equals(newOrderMenu.menu);
    }

    public Menu getMenu() {
        return menu;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }
}
