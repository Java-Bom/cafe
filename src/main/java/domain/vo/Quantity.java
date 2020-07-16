package domain.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Quantity {
    public static final int MIN_QUANTITY = 0;
    public static final int MAX_QUANTITY = 30;
    public static final Quantity ZERO = Quantity.of(0);

    private final int quantity;

    private Quantity(final int quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(final int quantity) {
        checkRange(quantity);
        return QuantityCache.CACHE.get(quantity);
    }

    public Quantity plus(final int quantity) {
        return Quantity.of(this.quantity + quantity);
    }

    public Quantity plus(final Quantity quantity) {
        return Quantity.of(this.quantity + quantity.quantity);
    }

    public int get() {
        return this.quantity;
    }

    private static void checkRange(final int quantity) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(String.format("수량: %d, %d-%d 수량만 가능합니다.", quantity, MIN_QUANTITY, MAX_QUANTITY));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        final Quantity quantity1 = (Quantity) o;
        return quantity == quantity1.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    private static class QuantityCache {
        static final Map<Integer, Quantity> CACHE = new HashMap<>();

        static {
            for (int idx = MIN_QUANTITY; idx <= MAX_QUANTITY; idx++) {
                CACHE.put(idx, new Quantity(idx));
            }
        }
    }
}
