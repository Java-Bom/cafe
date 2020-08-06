package com.javabom.cafe.dto.order;

import com.javabom.cafe.domain.order.OrderMenu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdersDto {
    private String menuName;
    private int orderAmount;
    private int price;

    public OrdersDto(final OrderMenu orderMenu) {
        this.menuName = orderMenu.getMenuName();
        this.orderAmount = orderMenu.getQuantity().getValue();
        this.price = orderMenu.calculatePrice();
    }
}
