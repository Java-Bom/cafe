package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import repository.MenuRepository;
import repository.OrderRepository;

//주문 메뉴와 수량을 알고있는 객체. OrderRepository는 이 객체를 통해 주문을 추가한다.
public class Bill {
	private final int tableNumber;
	Map<Menu, Integer> orderedMenus;

	public Bill(int tableNumber) {
		this.tableNumber = tableNumber;
		this.orderedMenus = Collections.emptyMap();
	}

	//해당 테이블에서 주문한 메뉴와 수량을 return한다.
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

}
