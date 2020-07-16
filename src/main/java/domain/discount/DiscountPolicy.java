package domain.discount;

import domain.menu.Menus;
import domain.vo.Money;

public interface DiscountPolicy {
    Money getDiscountAmount(Menus menus, Money amount);
}
