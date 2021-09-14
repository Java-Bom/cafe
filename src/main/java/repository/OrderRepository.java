package repository;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;

import domain.Order;

//주문 정보들을 저장하고, 정보를 제공해준다.
public class OrderRepository {
	private final List<Order> orders = new ArrayList<>();

	//주문을 받는다.
	public void addOrder(final Order order) {
		orders.add(order);
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

}