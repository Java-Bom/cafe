package domain.payment;

public enum PaymentType {
    CARD(1),
    CASH(0.9);

    private final double discountRate;

    PaymentType(final double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
