package domain.order;

import domain.menu.Menu;
import dto.order.OrdersDto;

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

}
