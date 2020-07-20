package domain.payment;

import domain.menu.OrderMenus;
import domain.vo.Amount;
import domain.vo.DiscountPriority;

public interface DiscountCondition {

    Amount getDiscountAmount(final OrderMenus orderMenus);

    DiscountPriority getDiscountPriority();
}
