package domain.discount;

import domain.menu.Menus;
import domain.vo.Money;

public class PercentDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_PERCENT = 10;

    @Override
    public Money getDiscountAmount(final Menus menus, Money amount) {
        return amount.divide(DISCOUNT_PERCENT);
    }
}
