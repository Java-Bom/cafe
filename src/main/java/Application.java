import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    private static final int ORDER = 1;
    private static final int PAY = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {

        final Cafe cafe = new Cafe();

        while (cafe.isOpen()) {
            OutputView.printMainWindow();
            int selectedFunction = InputView.inputFunctionNumber();

            if (selectedFunction == ORDER) {
                OutputView.printTables(cafe.getTables());
                final int tableNumber = InputView.inputTableNumber();
                OutputView.printMenus(cafe.getMenus());
                cafe.orderMenu(tableNumber, InputView.inputMenuNumber(), InputView.inputMenuQuantity());
                continue;
            }
            if (selectedFunction == PAY) {
                OutputView.printTables(cafe.getTables());
                final int tableNumber = InputView.inputTableNumber();
                OutputView.printOrderDetails(cafe.getOrderMenusTableOf(tableNumber));
                Payment payment = Payment.findByIndex(InputView.inputPayment(tableNumber));
                OutputView.printFinalPrice(cafe.payOrders(tableNumber, payment));
                continue;
            }
            if (selectedFunction == EXIT) {
                cafe.close();
            }
        }
    }
}
