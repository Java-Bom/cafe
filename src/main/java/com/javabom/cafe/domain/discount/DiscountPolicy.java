package com.javabom.cafe.domain.discount;

import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.vo.Money;

public interface DiscountPolicy {
    Money getDiscountAmount(Table table, Money amount);
}
