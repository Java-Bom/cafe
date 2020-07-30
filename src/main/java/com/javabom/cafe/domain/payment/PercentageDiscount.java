package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.domain.vo.DiscountPriority;

import java.util.List;

public class PercentageDiscount implements DiscountCondition {

    private final double discountRate;

    private final DiscountPriority discountPriority = DiscountPriority.valueOf(2);

    public PercentageDiscount(final double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public Amount getDiscountedAmount(final List<OrderMenu> orderMenus, Amount totalAmount) {
        return totalAmount.multiplyValue(discountRate);
    }

    @Override
    public DiscountPriority getDiscountPriority() {
        return discountPriority;
    }
}
