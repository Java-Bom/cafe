package repository;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Bill;
import domain.Menu;
import domain.Order;

//주문 정보들을 저장하고 주문한 것들의 합을 계산한다.
public class OrderRepository {
	private static final int MAX_NUMBER_PER_MENU = 30;
	private final List<Order> orders = new ArrayList<>();

	//주문을 받는다.
	public void addOrder(final Order order) {
		orders.add(order);
	}

	//해당 테이블에서 주문한 해당 메뉴의 수량을 return한다.
	public int countMenusNumber(int tableNumber, int menuNumber) {
		return orders.stream()
			.filter(order -> order.isEqualTable(tableNumber))
			.filter(order -> order.isEqualMenuNumber(menuNumber))
			.mapToInt(order -> order.getMenuCount())
			.sum();
	}

	//해당 테이블에서 주문할 수 있는 한 메뉴의 최대 수량을 넘는지 아닌지 검사한다.
	public boolean checkMaximumPerMenu(int tableNumber, int menuNumber, int menuCount) {
		int existedMenusCount = countMenusNumber(tableNumber, menuNumber);
		return existedMenusCount + menuCount <= MAX_NUMBER_PER_MENU;
	}

	//해당 테이블에서 주문한 것들을 모두 return한다.
	public List<Order> findByTableNumber(int tableNumber) {
		return
			orders.stream()
				.filter(order -> order.isEqualTable(tableNumber))
				.collect(toList());
	}

	//지불이 완료되면 해당 테이블의 주문을 모두 삭제한다.
	public void finishedPayment(int tableNumber) {
		orders.removeIf(order -> order.isEqualTable(tableNumber));
	}

	//해당 테이블에서 주문한 케익의 갯수를 계산한다.
	public int getNumberOfCakes(Bill bill) {
		int numberOfCakes = 0;
		for (Map.Entry<Menu, Integer> entry : bill.getOrderedMenus().entrySet()) {
			numberOfCakes += entry.getKey().isThisCake() ? entry.getValue() : 0;
		}
		return numberOfCakes;
	}

	//해당 테이블에서 주문한 메뉴들 가격의 총 합을 계산한다.
	public long getSumOfPayment(Bill bill) {
		long totalSumOfPayment = 0;
		for (Map.Entry<Menu, Integer> entry : bill.getOrderedMenus().entrySet()) {
			totalSumOfPayment += entry.getKey().getPrice() * entry.getValue();
		}
		return totalSumOfPayment;
	}

}