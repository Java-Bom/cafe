package domain.enums;

import domain.discount.AmountDiscountPolicy;
import domain.discount.DiscountPolicy;
import domain.discount.PercentDiscountPolicy;
import domain.menu.Menus;
import domain.vo.Money;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public enum PayType {
    CARD(1, Arrays.asList(new AmountDiscountPolicy())),
    CASH(2, Arrays.asList(new AmountDiscountPolicy(), new PercentDiscountPolicy())),
    EMPTY(-1, Collections.EMPTY_LIST);

    private final int type;
    private final List<DiscountPolicy> discountPolicies;

    PayType(final int type, final List<DiscountPolicy> discountPolicies) {
        this.type = type;
        this.discountPolicies = discountPolicies;
    }

    public static PayType findByType(final int paymentMethod) {
        return Arrays.stream(values())
                .filter(payType -> payType.equalsByType(paymentMethod))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("결제수단: %d, 존재하지 않는 결제수단입니다.", paymentMethod)));
    }

    private boolean equalsByType(final int paymentMethod) {
        return this.type == paymentMethod;
    }

    public Money calculateDiscountFee(Menus menus) {
        Money amount = menus.getAmount();
        for (DiscountPolicy policy : this.discountPolicies) {
            amount = amount.minus(policy.getDiscountAmount(menus, amount));
        }
        return amount;
    }
}
