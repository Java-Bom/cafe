package domain;

import java.util.Arrays;

public enum Payment {
    CARD(1, i -> i),
    CASH(2, i -> (int) ((double) i * 0.9));

    private final int index;
    private final FinalPayStrategy finalPayStrategy;

    Payment(final int index, final FinalPayStrategy finalPayStrategy) {
        this.index = index;
        this.finalPayStrategy = finalPayStrategy;
    }

    public static Payment findByIndex(int index) {
        return Arrays.stream(values())
                .filter(pay -> pay.index == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int calculateFinalPrice(int price) {
        return this.finalPayStrategy.disCountPrice(price);
    }

    interface FinalPayStrategy {
        int disCountPrice(final int originalPrice);
    }
}
