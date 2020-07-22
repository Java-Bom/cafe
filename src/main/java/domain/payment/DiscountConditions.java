package domain.payment;

import domain.menu.OrderMenus;
import domain.registration.Order;
import domain.vo.Amount;

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

    public Amount getDiscountAmount(final Order order) {
        OrderMenus orderMenus = order.getOrderMenus();
        Amount totalAmount = orderMenus.getTotalAmount();

        for (DiscountCondition discountCondition : discountConditions) {
            totalAmount = discountCondition.getDiscountAmount(orderMenus);
        }

        return totalAmount;
    }
}
