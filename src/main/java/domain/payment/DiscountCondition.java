package domain.payment;

import domain.vo.Amount;

public interface DiscountCondition {

    Amount getDiscountAmount(final Amount amount);
}
