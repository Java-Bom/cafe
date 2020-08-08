package com.javabom.cafe.domain.discount;

import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.vo.Money;
import com.javabom.cafe.domain.vo.Quantity;

public class AmountDiscountPolicy implements DiscountPolicy {
    private static final int VALID_AMOUNT = 3;
    private static final MenuType VALID_MENU_TYPE = MenuType.CAKE;
    private static final int DISCOUNT_FEE = 3_000;

    @Override
    public Money getDiscountAmount(final Table table, Money amount) {
        Quantity allQuantity = table.getQuantityBy(VALID_MENU_TYPE);
        return Money.of(allQuantity.get() / VALID_AMOUNT * DISCOUNT_FEE);
    }
}
