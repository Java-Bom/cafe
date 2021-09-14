package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import repository.MenuRepository;
import repository.OrderRepository;

//주문 메뉴와 수량을 알고있는 객체. 계산과 관련된 것을 책임진다.
public class Bill {
	private static final int DISCOUNT_FOR_CAKES = 3;
	private static final int DISCOUNT_MONEY_PER_NUM_OF_CAKES = 3000;
	private static final int MAX_NUMBER_PER_MENU = 30;

	private final int tableNumber;
	private Map<Menu, Integer> orderedMenus;

	public Bill(int tableNumber) {
		this.tableNumber = tableNumber;
		this.orderedMenus = Collections.emptyMap();
	}

	//생성
	public Bill(OrderRepository orderRepository, int tableNumber) {
		List<Order> orders = orderRepository.findByTableNumber(tableNumber);
		Map<Menu, Integer> orderedMenus = orders.stream()
			.filter(order -> order.getTableNumber() == tableNumber)
			.collect(Collectors.toMap(order -> MenuRepository.findByNumber(order.getMenuNumber()),
				order -> order.getMenuCount(), (c1, c2) -> c1 + c2));

		this.tableNumber = tableNumber;
		this.orderedMenus = orderedMenus;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public Map<Menu, Integer> getOrderedMenus() {
		return this.orderedMenus;
	}

	//최대수량 검사
	public boolean checkMaximumPerMenu(int menuNumber, int menuCount) {
		int existedMenusCount = this.countMenusNumber(menuNumber);
		return existedMenusCount + menuCount <= MAX_NUMBER_PER_MENU;
	}

	//해당 테이블에서 주문한 해당 메뉴의 수량을 return한다.
	public int countMenusNumber(int menuNumber) {
		int menuCount = 0;
		for (Menu key : orderedMenus.keySet()) {
			if (key.getNumber() != menuNumber)
				continue;
			menuCount = orderedMenus.get(key);
			break;
		}
		return menuCount;
	}

	//해당 테이블의 bill을 통해 주문한 케익의 갯수를 계산한다.
	public int getNumberOfCakes() {
		int numberOfCakes = 0;
		for (Map.Entry<Menu, Integer> entry : this.orderedMenus.entrySet()) {
			numberOfCakes += entry.getKey().isThisCake() ? entry.getValue() : 0;
		}
		return numberOfCakes;
	}

	//해당 테이블의 bill을 통해 주문한 메뉴들 가격의 총 합을 계산한다.
	public long getSumOfPayment() {
		long totalSumOfPayment = 0;
		for (Map.Entry<Menu, Integer> entry : this.orderedMenus.entrySet()) {
			totalSumOfPayment += entry.getKey().getPrice() * entry.getValue();
		}
		return totalSumOfPayment;
	}

	//할인을 적용한 최종 금액을 계산한다.
	public long getDiscountedPayment(int discountRate) {
		long totalSumOfPayment = this.getSumOfPayment();
		int numberOfCakes = this.getNumberOfCakes();
		if (numberOfCakes >= DISCOUNT_FOR_CAKES) {
			totalSumOfPayment -= DISCOUNT_MONEY_PER_NUM_OF_CAKES * (numberOfCakes / 3);
		}
		totalSumOfPayment = (long)(totalSumOfPayment - totalSumOfPayment * ((float)discountRate / 100));
		return totalSumOfPayment;
	}

}
