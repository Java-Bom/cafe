import java.util.List;
import java.util.NoSuchElementException;

import domain.Bill;
import domain.Menu;
import domain.PayType;
import domain.Table;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.PayTypeRepository;
import repository.TableRepository;
import service.CafeOrderService;
import view.InputView;
import view.OutputView;

public class Application {
	final static int ORDER = 1;
	final static int PAY = 2;
	final static int STOP = 3;

	// TODO 구현 진행
	public static void main(String[] args) {
		OutputView.printMain();
		final CafeOrderService cafeOrderService = new CafeOrderService(new OrderRepository());
		final List<Table> tables = TableRepository.tables();
		int func = InputView.inputFunction();

		while (func != STOP) {
			if (func == ORDER) {
				orderMenu(tables, cafeOrderService);
			}
			if (func == PAY) {
				payOrders(tables, cafeOrderService);
			} else if (func > STOP || func < ORDER) {
				throw new NoSuchElementException("해당 기능은 존재하지 않습니다.");
			}
			OutputView.printMain();
			func = InputView.inputFunction();
		}
	}

	private static void payOrders(List<Table> tables, CafeOrderService cafeOrderService) {
		OutputView.printTables(tables, cafeOrderService);
		int tableNum = InputView.inputPayTableNumber();
		if (!cafeOrderService.checkOrderedTable(tableNum)) {
			OutputView.printNoOrder();
			return;
		}
		Bill bill = cafeOrderService.getBillByTable(tableNum);
		OutputView.printBill(bill);
		OutputView.printPayMessage(tableNum);
		int payTypeNumber = InputView.inputPayType();
		PayType payType = PayTypeRepository.findByNumber(payTypeNumber);
		long amountOfPayment = cafeOrderService.getAmountOfPayment(bill, payType);
		OutputView.printAmountOfPayment(amountOfPayment);
	}

	private static void orderMenu(List<Table> tables, CafeOrderService cafeOrderService) {
		OutputView.printTables(tables, cafeOrderService);
		int tableNumber = InputView.inputTableNumber();
		boolean isValidTableNumber = cafeOrderService.isValidTableNumber(tableNumber);
		if (!isValidTableNumber) {
			orderMenu(tables, cafeOrderService);
		}
		final List<Menu> menus = MenuRepository.menus();
		OutputView.printMenus(menus);
		int menuNumber = InputView.inputMenu();
		boolean isValidMenuNum = cafeOrderService.isValidMenuNumber(menuNumber);
		if (!isValidMenuNum) {
			OutputView.printMenuAlert();
			orderMenu(tables, cafeOrderService);
		}
		int menuCount = InputView.inputCount();
		boolean isMaxMenuCount = cafeOrderService.checkMaximumCount(tableNumber, menuNumber, menuCount);
		if (!isMaxMenuCount) {
			OutputView.printMaxAlert();
			return;
		}
		cafeOrderService.orderMenu(tableNumber, menuNumber, menuCount);
	}
}
