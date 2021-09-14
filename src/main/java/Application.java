import java.util.NoSuchElementException;

import domain.Bill;
import domain.PayType;
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
		int selectedFunction = InputView.inputFunction();

		while (selectedFunction != STOP) {
			if (selectedFunction == ORDER) {
				orderMenu(cafeOrderService);
			}
			if (selectedFunction == PAY) {
				payOrders(cafeOrderService);
			}
			if (!cafeOrderService.isValidFunction(selectedFunction, STOP, ORDER)) {
				throw new NoSuchElementException("해당 기능은 존재하지 않습니다.");
			}
			OutputView.printMain();
			selectedFunction = InputView.inputFunction();
		}
	}

	private static void payOrders(CafeOrderService cafeOrderService) {
		OutputView.printTables(cafeOrderService);
		int tableNumber = InputView.inputPayTableNumber();
		if (!cafeOrderService.checkOrderedTable(tableNumber)) {
			OutputView.printNoOrder();
			return;
		}
		Bill bill = cafeOrderService.getBillByTable(tableNumber);
		OutputView.printBill(bill);
		OutputView.printPayMessage(tableNumber);
		int payTypeNumber = InputView.inputPayType();
		PayType payType = cafeOrderService.findPayType(payTypeNumber);
		long amountOfPayment = cafeOrderService.getAmountOfPayment(bill, payType);
		OutputView.printAmountOfPayment(amountOfPayment);
	}

	private static void orderMenu(CafeOrderService cafeOrderService) {
		OutputView.printTables(cafeOrderService);
		int tableNumber = InputView.inputTableNumber();
		if (!cafeOrderService.isValidTableNumber(tableNumber)) {
			orderMenu(cafeOrderService);
		}
		OutputView.printMenus();
		int menuNumber = InputView.inputMenu();
		if (!cafeOrderService.isValidMenuNumber(menuNumber)) {
			OutputView.printMenuAlert();
			orderMenu(cafeOrderService);
		}
		int menuCount = InputView.inputCount();
		if (!cafeOrderService.checkMaximumCount(tableNumber, menuNumber, menuCount)) {
			OutputView.printMaxAlert();
			return;
		}
		cafeOrderService.orderMenu(tableNumber, menuNumber, menuCount);
	}
}
