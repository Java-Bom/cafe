package service;

import java.util.Map;

import domain.Bill;
import domain.Menu;
import domain.Order;
import domain.PayType;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.TableRepository;

//카페의 주문과 관련된 서비스를 제공하는 클래스
public class CafeOrderService {
	private static final int DISCOUNT_FOR_CAKES = 3;
	private static final int DISCOUNT_MONEY_PER_NUM_OF_CAKES = 3000;

	private final OrderRepository orderRepository;

	public CafeOrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	//해당 테이블이 존재하는 테이블인지 검사한다.
	public boolean isValidTableNumber(int tableNumber) {
		if (TableRepository.findByNumber(tableNumber).isPresent()) {
			return true;
		}
		return false;
	}

	//주문하려는 메뉴의 수량이 누적 30개가 넘는지 확인한다.
	public boolean checkMaximumCount(int tableNumber, int menuNumber, int menuCount) {
		return orderRepository.checkMaximumPerMenu(tableNumber, menuNumber, menuCount);
	}

	// 주문을 추가한다.
	public void orderMenu(int tableNumber, int menuNumber, int menuCount) {
		orderRepository.addOrder(new Order(tableNumber, menuNumber, menuCount));
	}

	//해당 테이블에서 주문한 것들에 대한 영수증을 return한다.
	public Bill getBillByTable(int tableNumber) {
		return new Bill(orderRepository, tableNumber);
	}

	//OrderedTable이면 True return
	public boolean checkOrderedTable(int tableNumber) {
		Map<Menu, Integer> orderedMenus = getBillByTable(tableNumber).getOrderedMenus();

		return orderedMenus != null && !orderedMenus.isEmpty();
	}

	//입력된 메뉴가 존재하는 메뉴인지 검사한다.
	public boolean isValidMenuNumber(int menuNumber) {
		return MenuRepository.checkMenuNumber(menuNumber) ? true : false;
	}

	//지불해야하는 총 금액을 return한다.
	public long getAmountOfPayment(Bill bill, PayType payType) {
		int numberOfCakes = orderRepository.getNumberOfCakes(bill);
		long amountOfPayment = getFinalPayment(bill, numberOfCakes, payType.getDiscountRate());
		orderRepository.finishedPayment(bill.getTableNumber());

		return amountOfPayment;
	}

	//할인을 적용한 최종 금액을 계산한다.
	private long getFinalPayment(Bill bill, int numberOfCakes, int discountRate) {
		long totalSumOfPayment = orderRepository.getSumOfPayment(bill);
		if (numberOfCakes >= DISCOUNT_FOR_CAKES) {
			totalSumOfPayment -= DISCOUNT_MONEY_PER_NUM_OF_CAKES * (numberOfCakes / 3);
		}
		totalSumOfPayment = (long)(totalSumOfPayment - totalSumOfPayment * ((float)discountRate / 100));
		return totalSumOfPayment;
	}
}
