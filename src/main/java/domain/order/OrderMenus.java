package domain.order;

import domain.menu.Menu;

import java.util.List;
import java.util.Optional;

public class OrderMenus {
    private static final int MAX_QUANTITY = 30;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(final List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public void isPossibleAdd(Menu menu, int quantity) {
        Optional<Quantity> quantitySum = orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameMenu(menu))
                .map(OrderMenu::getQuantity)
                .reduce(Quantity::add);

        Quantity orderQuantity = quantitySum.isPresent() ? quantitySum.get().add(new Quantity(quantity))
                : new Quantity(quantity);

        validateMaxQuantity(orderQuantity);
    }

    private void validateMaxQuantity(final Quantity quantity) {
        if (quantity.getValue() > MAX_QUANTITY) {
            throw new IllegalArgumentException("주문 가능 수량을 초과 하였습니다. - " + quantity);
        }
    }

}
