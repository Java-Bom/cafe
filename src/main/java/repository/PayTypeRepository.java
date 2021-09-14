package repository;

import java.util.ArrayList;
import java.util.List;

import domain.PayType;

public class PayTypeRepository {
	public static final List<PayType> payTypes = new ArrayList<>();

	static {
		payTypes.add(new PayType(1, 0));
		payTypes.add(new PayType(2, 10));
	}

	public static PayType findByNumber(int number) {
		return payTypes.stream()
			.filter(payType -> payType.isEqualPayType(number))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("결제 수단은 카드(1)와 현금(2)만 존재합니다."));
	}
}
