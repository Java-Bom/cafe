package domain;

//지불 수단
public class PayType {
	private final int number;
	private final int discountRate;

	public PayType(int number, int discountRate) {
		this.number = number;
		this.discountRate = discountRate;
	}

	public boolean isEqualPayType(int number) {
		return this.number == number;
	}

	public int getDiscountRate() {
		return this.discountRate;
	}

}
