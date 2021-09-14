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
	private final OrderRepository orderRepository;

	public CafeOrderService() {
		this.orderRepository = new OrderRepository();
	}

	//사용자가 선택한 기능이 valid한지 검사한다.
	public static boolean isValidFunction(int selectedFunction, int STOP, int ORDER) {
		return selectedFunction <= STOP || selectedFunction >= ORDER;
	}

	//해당 테이블이 존재하는 테이블인지 검사한다.
	public boolean isValidTableNumber(int tableNumber) {
		return TableRepository.findByNumber(tableNumber).isPresent() ? true : false;
	}

	//주문하려는 메뉴의 수량이 누적 30개가 넘는지 확인한다.
	public boolean checkMaximumCount(int tableNumber, int menuNumber, int menuCount) {
		return new Bill(orderRepository, tableNumber).checkMaximumPerMenu(menuNumber, menuCount);
	}

	// 주문을 추가한다.
	public void orderMenu(int tableNumber, int menuNumber, int menuCount) {
		orderRepository.addOrder(new Order(tableNumber, menuNumber, menuCount));
	}

	//해당 테이블에서 주문한 것들에 대한 영수증을 return한다.
	public Bill getBillByTable(int tableNumber) {
		return new Bill(orderRepository, tableNumber);
	}

	//OrderedTable이면 return true
	public boolean checkOrderedTable(int tableNumber) {
		Map<Menu, Integer> orderedMenus = getBillByTable(tableNumber).getOrderedMenus();

		return orderedMenus != null && !orderedMenus.isEmpty();
	}

	//입력된 메뉴가 존재하는 메뉴인지 검사한다.
	public boolean isValidMenuNumber(int menuNumber) {
		return MenuRepository.checkMenuNumber(menuNumber) ? true : false;
	}

	//최종 금액을 return 하고, orderRepository에서 주문을 삭제한다.
	public long getAmountOfPayment(Bill bill, PayType payType) {
		long discountedPayment = bill.getDiscountedPayment(payType.getDiscountRate());
		orderRepository.finishedPayment(bill.getTableNumber());

		return discountedPayment;
	}

}
