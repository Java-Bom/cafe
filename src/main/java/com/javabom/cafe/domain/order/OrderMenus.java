package com.javabom.cafe.domain.order;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.dto.order.OrderPayDto;
import com.javabom.cafe.dto.order.OrdersDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMenus {
    private static final int MAX_QUANTITY = 30;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(final List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public Optional<OrderMenu> findByMenu(final Menu menu) {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameMenu(menu))
                .findFirst();
    }

    public List<OrdersDto> getOrders() {
        return orderMenus.stream()
                .map(OrdersDto::new)
                .collect(Collectors.toList());
    }

    private void validateMaxQuantity(final Quantity quantity) {
        if (quantity.getValue() > MAX_QUANTITY) {
            throw new IllegalArgumentException("주문 가능 수량을 초과 하였습니다. - " + quantity);
        }
    }

    public OrderPayDto calculatePayment() {
        List<OrderMenu> cakeOrders = orderMenus.stream()
                .filter(OrderMenu::isCake)
                .collect(Collectors.toList());

        List<OrderMenu> beverageOrders = orderMenus.stream()
                .filter(OrderMenu::isBeverage)
                .collect(Collectors.toList());

        double cashPrice = Payment.calculateCash(cakeOrders, beverageOrders);
        double cardPrice = Payment.calculateCard(cakeOrders, beverageOrders);

        return new OrderPayDto(cashPrice, cardPrice);
    }

}
