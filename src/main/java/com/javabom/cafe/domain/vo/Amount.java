package com.javabom.cafe.domain.vo;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Amount {

    private double value;

    private Amount() {
    }

    private Amount(final int value) {
        this.value = value;
    }

    private Amount(final double value) {
        this.value = value;
    }

    public static Amount valueOf(final int value) {
        return new Amount(value);
    }

    public static Amount valueOf(final double value) {
        return new Amount(value);
    }

    public static Amount sum(final List<Amount> amounts) {
        int value = 0;

        for (Amount amount : amounts) {
            value += amount.value;
        }

        return Amount.valueOf(value);
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

    public double getValue() {
        return value;
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
