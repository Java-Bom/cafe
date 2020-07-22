package domain.menu;

import domain.vo.Amount;
import domain.vo.Quantity;

import java.util.Objects;

public class OrderMenu {

    private final Menu menu;

    private Quantity quantity;

    public OrderMenu(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void extraOrder(final OrderMenu orderMenu) {
        this.quantity = quantity.addQuantity(orderMenu.quantity);
    }

    public boolean isSameMenu(final OrderMenu orderMenu) {
        return this.menu.equals(orderMenu.menu);
    }

    public boolean isSameCategory(Category category) {
        return menu.isSameCategory(category);
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Amount getTotalAmount() {
        return this.menu.getTotalAmount(quantity.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderMenu orderMenu = (OrderMenu) o;
        return Objects.equals(menu, orderMenu.menu) &&
                Objects.equals(getQuantity(), orderMenu.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, getQuantity());
    }

}
