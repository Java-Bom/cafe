import java.util.List;
import java.util.NoSuchElementException;

import domain.Bill;
import domain.Menu;
import domain.PayType;
import domain.Table;
import repository.MenuRepository;
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
		final CafeOrderService cafeOrderService = new CafeOrderService();
		final List<Table> tables = TableRepository.tables();
		int selectedFunction = InputView.inputFunction();

		while (selectedFunction != STOP) {
			if (selectedFunction == ORDER) {
				orderMenu(tables, cafeOrderService);
			}
			if (selectedFunction == PAY) {
				payOrders(tables, cafeOrderService);
			}
			if (!cafeOrderService.isValidFunction(selectedFunction, STOP, ORDER)) {
				throw new NoSuchElementException("해당 기능은 존재하지 않습니다.");
			}
			OutputView.printMain();
			selectedFunction = InputView.inputFunction();
		}
	}

	private static void payOrders(List<Table> tables, CafeOrderService cafeOrderService) {
		OutputView.printTables(tables, cafeOrderService);
		int tableNumber = InputView.inputPayTableNumber();
		if (!cafeOrderService.checkOrderedTable(tableNumber)) {
			OutputView.printNoOrder();
			return;
		}
		Bill bill = cafeOrderService.getBillByTable(tableNumber);
		OutputView.printBill(bill);
		OutputView.printPayMessage(tableNumber);
		int payTypeNumber = InputView.inputPayType();
		PayType payType = PayTypeRepository.findByNumber(payTypeNumber);
		long amountOfPayment = cafeOrderService.getAmountOfPayment(bill, payType);
		OutputView.printAmountOfPayment(amountOfPayment);
	}

	private static void orderMenu(List<Table> tables, CafeOrderService cafeOrderService) {
		OutputView.printTables(tables, cafeOrderService);
		int tableNumber = InputView.inputTableNumber();
		if (!cafeOrderService.isValidTableNumber(tableNumber)) {
			orderMenu(tables, cafeOrderService);
		}
		final List<Menu> menus = MenuRepository.menus();
		OutputView.printMenus(menus);
		int menuNumber = InputView.inputMenu();
		if (!cafeOrderService.isValidMenuNumber(menuNumber)) {
			OutputView.printMenuAlert();
			orderMenu(tables, cafeOrderService);
		}
		int menuCount = InputView.inputCount();
		if (!cafeOrderService.checkMaximumCount(tableNumber, menuNumber, menuCount)) {
			OutputView.printMaxAlert();
			return;
		}
		cafeOrderService.orderMenu(tableNumber, menuNumber, menuCount);
	}
}
