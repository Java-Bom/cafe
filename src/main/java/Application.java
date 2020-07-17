import domain.*;
import service.CafeService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        final CafeService cafeService = new CafeService(new OrderRepository());

        int selectNo = InputView.showMain();

        while (Pos.isExit(selectNo)) {
            if (Pos.isOrder(selectNo)) { // 주문
                OutputView.printTables(tables, cafeService);
                int tableNumber = InputView.inputTableNumber();
                OutputView.printMenus(MenuRepository.menus());
                int menuNumber = InputView.inputMenuNumber();
                int count = InputView.inputMenuCount();
                cafeService.orderMenu(menuNumber, count, tableNumber);
            }
            if (Pos.isCalculation(selectNo)) {
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
