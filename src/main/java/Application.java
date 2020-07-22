import domain.POS;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.table.Table;
import domain.table.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        final List<Menu> menus = MenuRepository.findAll();
        final List<Table> tables = TableRepository.findAll();
        POS pos = new POS();

        int function = 0;
        while (function != 3) {
            OutputView.printMainView();
            function = InputView.inputFunction();

            if (function == 1) {
                OutputView.printTables(tables, pos);
                int tableNumber = InputView.inputTableNumber();
                OutputView.printMenus(menus);
                int menuIdx = InputView.inputMenuIdx();
                int quantity = InputView.inputQuantity();
                pos.takeOrder(tableNumber, menuIdx, quantity);
            }

            if (function == 2) {
                int tableNumber = InputView.inputTableNumber();
                OutputView.printOrderHistory(pos.findOrderHistoryOf(tableNumber));
                OutputView.printPaymentOf(tableNumber);
                int paymentMethod = InputView.inputPaymentMethod();
                OutputView.printFinalAmount(pos.calculateFee(tableNumber, paymentMethod));
                pos.clearOf(tableNumber);
            }
        }
    }
}
