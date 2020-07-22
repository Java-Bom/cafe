package domain.discount;

import domain.order.Orders;
import domain.vo.Money;

public interface DiscountPolicy {
    Money getDiscountAmount(Orders orders, Money amount);
}
