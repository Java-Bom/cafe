package domain.vo;

import java.util.HashMap;
import java.util.Map;

public class Quantity {

    public static final int MAX_QUANTITY = 30;
    public static final int MIN_QUANTITY = 0;

    private final int value;

    private Quantity(final int value) {
        this.value = value;
    }

    private static void validQuantityValue(final int value) {
        if (value < MIN_QUANTITY || value > MAX_QUANTITY) {
            throw new IllegalArgumentException(
                    String.format("입력한 수량 : %d - 최대 30개까지 주문할 수 있습니다.", value));
        }
    }

    public static Quantity valueOf(int value) {
        validQuantityValue(value);
        return QuantityCache.getQuantity(value);
    }

    public Quantity addQuantity(final Quantity quantity) {
        int totalValue = this.value + quantity.value;

        return QuantityCache.getQuantity(totalValue);
    }

    public Quantity divideQuantity(final Quantity quantity) {
        if (quantity.value == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }

        return Quantity.valueOf(value / quantity.value);
    }

    public int getValue() {
        return value;
    }

    private static class QuantityCache {
        private static final Map<Integer, Quantity> CACHE;

        static {
            CACHE = new HashMap<>();
            for (int i = MIN_QUANTITY; i <= MAX_QUANTITY; i++) {
                CACHE.put(i, new Quantity(i));
            }
        }

        private static Quantity getQuantity(final int value) {
            return CACHE.get(value);
        }
    }
}
