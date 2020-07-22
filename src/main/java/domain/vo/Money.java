package domain.vo;

import java.util.Objects;

public class Money {
    public static final Money ZERO = Money.of(0);

    private final int fee;

    private Money(final int fee) {
        checkNegative(fee);
        this.fee = fee;
    }

    private void checkNegative(final int fee) {
        if (fee < 0) {
            throw new IllegalArgumentException(String.format("금액:%d, 금액은 0이상 입니다.", fee));
        }
    }

    public static Money of(final int number) {
        return new Money(number);
    }

    public Money divide(final int number) {
        checkDivideValue(number);
        return Money.of(this.fee / number);
    }

    private void checkDivideValue(final int number) {
        if (number == 0) {
            throw new IllegalArgumentException(String.format("숫자:%d, 0이 들어오면 안됩니다.", number));
        }
    }

    public Money minus(final Money price) {
        return Money.of(this.fee - price.fee);
    }

    public int get() {
        return fee;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        final Money money = (Money) o;
        return get() == money.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(get());
    }
}
