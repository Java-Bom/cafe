package domain.discount;

import domain.enums.Category;
import domain.order.Orders;
import domain.vo.Money;
import domain.vo.Quantity;

public class AmountDiscountPolicy implements DiscountPolicy {
    private static final int VALID_AMOUNT = 3;
    private static final Category VALID_CATEGORY = Category.CAKE;
    private static final int DISCOUNT_FEE = 3_000;

    @Override
    public Money getDiscountAmount(final Orders orders, Money amount) {
        Quantity allQuantity = orders.getQuantityBy(VALID_CATEGORY);
        return Money.of(allQuantity.get() / VALID_AMOUNT * DISCOUNT_FEE);
    }
}
