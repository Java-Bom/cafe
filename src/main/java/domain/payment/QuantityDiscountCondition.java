package domain.payment;

import domain.vo.Amount;
import domain.vo.Quantity;

public class QuantityDiscountCondition implements DiscountCondition {

    private static final Amount DISCOUNT_AMOUNT = Amount.valueOf(3_000);

    public static final Quantity DISCOUNT_QUANTITY = Quantity.valueOf(3);

    @Override
    public Amount getDiscountAmount(final Amount amount) {
        return amount.subtractionAmount(DISCOUNT_AMOUNT);
    }
}
