package domain.order;

import domain.menu.Menu;
import lombok.Getter;

public class OrderMenu {
    private static final int MAX_QUANTITY = 30;

    private final Menu menu;

    @Getter
    private Quantity quantity;

    public OrderMenu(final Menu menu, final Quantity quantity) {
        validateMaxQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public void addQuantity(final Quantity quantity) {
        Quantity afterAdd = this.quantity.add(quantity);

        validateMaxQuantity(afterAdd);

        this.quantity = afterAdd;
    }

    public boolean isSameMenu(final Menu menu) {
        return this.menu.equals(menu);
    }

    private void validateMaxQuantity(final Quantity quantity) {
        if (quantity.getValue() > MAX_QUANTITY) {
            throw new IllegalArgumentException("주문 가능 수량을 초과 하였습니다. - " + quantity);
        }
    }
}
