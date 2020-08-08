package com.javabom.cafe.domain.discount;

import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.vo.Money;

public class PercentDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_PERCENT = 10;

    @Override
    public Money getDiscountAmount(final Table table, Money amount) {
        return amount.divide(DISCOUNT_PERCENT);
    }
}
