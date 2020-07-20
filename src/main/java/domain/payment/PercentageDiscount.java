package domain.payment;

import domain.menu.OrderMenus;
import domain.vo.Amount;
import domain.vo.DiscountPriority;

public class PercentageDiscount implements DiscountCondition {

    private final double discountRate;

    private final DiscountPriority discountPriority = DiscountPriority.valueOf(2);

    public PercentageDiscount(final double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public Amount getDiscountAmount(final OrderMenus orderMenus) {
        return orderMenus.getDiscountAmount(discountRate);
    }

    @Override
    public DiscountPriority getDiscountPriority() {
        return discountPriority;
    }
}
