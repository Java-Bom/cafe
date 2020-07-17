package domain.menu;

import domain.payment.DiscountCondition;
import domain.payment.QuantityDiscountCondition;
import domain.vo.Amount;
import domain.vo.Quantity;

import java.util.Objects;

public class OrderMenu {

    private final Menu menu;

    private Quantity quantity;

    public OrderMenu(final Menu menu, final int quantity) {
        this.menu = menu;
        this.quantity = Quantity.valueOf(quantity);
    }

    public void extraOrder(final OrderMenu orderMenu) {
        this.quantity = quantity.addQuantity(orderMenu.quantity);
    }

    public Amount calculateDiscountAmount(DiscountCondition discountCondition) {
        int value = quantity.getValue();
        Amount amount = menu.getAmount().multiplyValue(value);
        Quantity discountQuantity = quantity.divideQuantity(QuantityDiscountCondition.DISCOUNT_QUANTITY);

        for (int i = 0; i < discountQuantity.getValue(); i++) {
            amount = discountCondition.getDiscountAmount(amount);
        }

        return amount;
    }


    public boolean isSameMenu(final OrderMenu orderMenu) {
        return this.menu.equals(orderMenu.menu);
    }

    public Quantity getQuantity() {
        return quantity;
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
