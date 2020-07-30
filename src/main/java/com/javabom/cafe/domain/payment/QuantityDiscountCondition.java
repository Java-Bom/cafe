package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.domain.vo.DiscountPriority;
import com.javabom.cafe.domain.vo.Quantity;

import java.util.List;
import java.util.stream.Collectors;

public class QuantityDiscountCondition implements DiscountCondition {

    public static final MenuType DISCOUNT_MENU_TYPE = MenuType.CAKE;

    public static final Amount DISCOUNT_AMOUNT = Amount.valueOf(3_000);

    public static final Quantity DISCOUNT_QUANTITY = Quantity.valueOf(3);

    private final DiscountPriority discountPriority = DiscountPriority.valueOf(1);

    @Override
    public Amount getDiscountedAmount(final List<OrderMenu> orderMenus, Amount totalAmount) {
        List<OrderMenu> discountMenu = orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameCategory(DISCOUNT_MENU_TYPE))
                .collect(Collectors.toList());

        Amount discountAmount = getDiscountAmount(discountMenu);

        return totalAmount.subtractionAmount(discountAmount);
    }

    private Amount getDiscountAmount(final List<OrderMenu> discountMenus) {
        List<Amount> discountAmount = discountMenus.stream()
                .map(OrderMenu::getQuantity)
                .map(quantity -> quantity.divideQuantity(DISCOUNT_QUANTITY))
                .map(quantity -> DISCOUNT_AMOUNT.multiplyValue(quantity.getValue()))
                .collect(Collectors.toList());

        return Amount.sum(discountAmount);
    }

    @Override
    public DiscountPriority getDiscountPriority() {
        return discountPriority;
    }
}
