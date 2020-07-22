package domain.payment;

import domain.menu.Category;
import domain.menu.OrderMenus;
import domain.vo.Amount;
import domain.vo.DiscountPriority;
import domain.vo.Quantity;

public class QuantityDiscountCondition implements DiscountCondition {

    public static final Category discountCategory = Category.CAKE;

    public static final Amount DISCOUNT_AMOUNT = Amount.valueOf(3_000);

    public static final Quantity DISCOUNT_QUANTITY = Quantity.valueOf(3);

    private final DiscountPriority discountPriority = DiscountPriority.valueOf(1);

    @Override
    public Amount getDiscountAmount(final OrderMenus menus) {
        return menus.getDiscountAmount(DISCOUNT_QUANTITY, DISCOUNT_AMOUNT, discountCategory);
    }

    @Override
    public DiscountPriority getDiscountPriority() {
        return discountPriority;
    }
}
