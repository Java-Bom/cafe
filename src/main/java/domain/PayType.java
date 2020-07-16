package domain;

import java.util.Arrays;

public enum PayType {
    CASH(1, 0.9f), CARD(2, 1);

    private final int number;
    private final float discountRate;

    PayType(final int number, final float discountRate) {
        this.number = number;
        this.discountRate = discountRate;
    }

    public static PayType findByNumber(final int number) {
        return Arrays.stream(values())
                .filter(payType -> payType.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 결제방법은 존재하지 않습니다"));
    }

    public float getDiscountRate() {
        return discountRate;
    }
}
