package domain.payment;

import domain.vo.Amount;

public class PaymentTypeDiscountCondition implements DiscountCondition {

    private final PaymentType PAYMENT_CONDITION = PaymentType.CASH;

    @Override
    public Amount getDiscountAmount(final Amount amount) {
        return amount.multiplyValue(PAYMENT_CONDITION.getDiscountRate());
    }
}
