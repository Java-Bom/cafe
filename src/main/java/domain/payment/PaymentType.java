package domain.payment;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PaymentType {
    CARD(1,
            DiscountConditions.of(new QuantityDiscountCondition())),
    CASH(2,
            DiscountConditions.of(new QuantityDiscountCondition(), new PercentageDiscount(0.9)));

    private final int number;

    private final DiscountConditions discountConditions;

    PaymentType(final int number, final DiscountConditions discountConditions) {
        this.number = number;
        this.discountConditions = discountConditions;
    }

    public static PaymentType findByNumber(final int number) {
        return Arrays.stream(PaymentType.values())
                .filter(paymentType -> paymentType.number == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("입력한 결제 번호 : %d - 해당 결제 번호는 존재하지 않습니다.", number)));
    }

    public DiscountConditions getDiscountConditions() {
        return discountConditions;
    }
}
