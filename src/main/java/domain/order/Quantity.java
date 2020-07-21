package domain.order;

import lombok.Getter;

@Getter
public class Quantity {
    private static final int MIN_QUANTITY = 1;

    private final int value;

    public Quantity(final int value) {
        validateQuantity(value);
        this.value = value;
    }

    public Quantity add(final Quantity quantity) {
        return new Quantity(value + quantity.value);
    }

    private void validateQuantity(final int value) {
        if (value < MIN_QUANTITY) {
            throw new IllegalArgumentException("주문 수량은 1 보다 작을 수 없습니다. - " + value);
        }
    }

    @Override
    public String toString() {
        return value + "개";
    }
}
