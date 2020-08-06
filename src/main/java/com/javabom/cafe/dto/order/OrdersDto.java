package com.javabom.cafe.dto.order;

import com.javabom.cafe.domain.order.OrderMenu;

public class OrdersDto {
    private String name;
    private int amount;
    private int money;

    public OrdersDto(final OrderMenu orderMenu) {
        this.name = orderMenu.getMenuName();
        this.amount = orderMenu.getQuantity().getValue();
        this.money = orderMenu.calculatePrice();
    }
}
