package com.javabom.cafe.dto.order;

import com.javabom.cafe.domain.vo.Quantity;

public class OrderFindAllResDto {

    private String menuName;
    private Quantity quantity;
    private int price;

    public OrderFindAllResDto(final String menuName, final Quantity quantity, final int price) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }
}
