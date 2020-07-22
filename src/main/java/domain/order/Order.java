package domain.order;

import domain.enums.Category;
import domain.menu.Menu;
import domain.vo.Quantity;

import java.util.Objects;

public class Order {
    private final Menu menu;
    private Quantity quantity;

    public Order(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void addQuantity(final Order order) {
        this.quantity = this.quantity.plus(order.quantity);
    }

    public int getAmount() {
        return menu.getMultiplyPriceOf(quantity);
    }

    public int getQuantityBy(final Category category) {
        if (menu.equalsOf(category)) {
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

    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        final Order order = (Order) o;
        return Objects.equals(getMenu(), order.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenu());
    }
}
