package domain;

import java.util.Arrays;

public enum PayType {
    CARD(1, 0), CASH(2,10);

    private final int number;
    private final int discountRate;

    PayType(int number, int discountRate){
        this.number = number;
        this.discountRate = discountRate;
    }

    public static PayType findByNumber(int number){
        return Arrays.stream(values())
                .filter(payType -> payType.number==number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결제 수단은 카드(1)와 현금(2)만 존재합니다."));
    }

    public int getDiscountRate(){
        return this.discountRate;
    }

}
