import domain.*;
import service.CafeService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        final CafeService cafeService = new CafeService(new OrderRepository());

        int selectNo = InputView.showMain();

        while (selectNo != 3) {
            if (selectNo == 1) { // 주문
                OutputView.printTables(tables, cafeService);
                int tableNumber = InputView.inputTableNumber();
                OutputView.printMenus(MenuRepository.menus());
                int menuNumber = InputView.inputMenuNumber();
                int count = InputView.inputMenuCount();
                cafeService.orderMenu(menuNumber, count, tableNumber);
            }
            if (selectNo == 2) {
                OutputView.printTables(tables, cafeService);
                int tableNumber = InputView.inputTableNumberForCalculate();
                OutputView.printOrders(cafeService, tableNumber);
                int payType = InputView.askPayType(tableNumber);
                long totalPrice = cafeService.calculate(tableNumber, PayType.findByNumber(payType));
                OutputView.printTotalPrice(totalPrice);
            }
            selectNo = InputView.showMain();
        }
    }
}
