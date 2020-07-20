package domain.vo;

public class DiscountPriority implements Comparable<DiscountPriority> {

    private final int value;

    private DiscountPriority(final int value) {
        this.value = value;
    }

    public static DiscountPriority valueOf(final int value) {
        return new DiscountPriority(value);
    }

    @Override
    public int compareTo(final DiscountPriority discountPriority) {
        return this.value - discountPriority.value;
    }
}
