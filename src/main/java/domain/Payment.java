package domain;

import java.util.Arrays;

public enum Payment {
    CARD(1, i -> i),
    CASH(2, i -> (int) ((double) i * 0.9));

    private final int index;
    private final PayStrategy payStrategy;

    Payment(final int index, final PayStrategy payStrategy) {
        this.index = index;
        this.payStrategy = payStrategy;
    }

    public static Payment findByIndex(int index) {
        return Arrays.stream(values())
                .filter(pay -> pay.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d번 결제 수단은 존재하지 않습니다.", index)));
    }

    public int calculateFinalPrice(int price) {
        return this.payStrategy.disCountPrice(price);
    }

    interface PayStrategy {
        int disCountPrice(final int originalPrice);
    }
}
