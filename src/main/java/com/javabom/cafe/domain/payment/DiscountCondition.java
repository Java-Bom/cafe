package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.domain.vo.DiscountPriority;

import java.util.List;

public interface DiscountCondition {

    Amount getDiscountedAmount(final List<OrderMenu> orderMenus, Amount totalAmount);

    DiscountPriority getDiscountPriority();
}
