package domain.vo;

import java.util.List;
import java.util.Objects;

public class Amount {

    private final double value;

    private Amount(final int value) {
        this.value = value;
    }

    private Amount(final double value) {
        this.value = value;
    }

    public static Amount valueOf(final int value) {
        return new Amount(value);
    }

    public Amount getAmount() {
        return new Amount(value);
    }

    public Amount multiplyValue(final double value) {
        return new Amount(this.value * value);
    }

    public Amount multiplyValue(final int value) {
        return new Amount(this.value * value);
    }

    public Amount add(final Amount amount) {
        return new Amount(this.value + amount.value);
    }

    public Amount subtractionAmount(final Amount amount) {
        return new Amount(value - amount.value);
    }


    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Amount amount = (Amount) o;
        return Double.compare(amount.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
