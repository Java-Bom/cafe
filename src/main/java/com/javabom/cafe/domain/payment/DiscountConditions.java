package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DiscountConditions {

    private final List<DiscountCondition> discountConditions = new ArrayList<>();

    public static DiscountConditions of(DiscountCondition... discountConditions) {
        DiscountConditions conditions = new DiscountConditions();
        conditions.discountConditions.addAll(Arrays.asList(discountConditions));
        conditions.sortByPriority();
        return conditions;
    }

    public void sortByPriority() {
        discountConditions.sort(Comparator.comparing(DiscountCondition::getDiscountPriority));
    }

    public Amount getDiscountAmount(final CafeTable cafeTable) {
        List<OrderMenu> orderMenus = cafeTable.getOrderMenus();
        Amount totalAmount = cafeTable.getTotalAmount();

        for (DiscountCondition discountCondition : discountConditions) {
            totalAmount = discountCondition.getDiscountedAmount(orderMenus, totalAmount);
        }

        return totalAmount;
    }
}
