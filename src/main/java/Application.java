import domain.*;
import view.InputView;
import view.OutputView;

import static domain.MenuRepository.findMenuByNumber;
import static domain.TableRepository.findTableByNumber;

public class Application {
    private static final int ORDER = 1;
    private static final int PAY = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {

        final Cafe cafe = new Cafe(TableRepository.tables(), MenuRepository.menus());
        int tableNumber;
        int menuNumber;
        int selectedFunction;

        while (cafe.isOpen()) {
            OutputView.printMainWindow();
            selectedFunction = InputView.inputFunctionNumber();

            if (selectedFunction == ORDER) {
                OutputView.printTables(cafe.getTables());
                try {
                    tableNumber = InputView.inputTableNumber();
                    findTableByNumber(tableNumber);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                OutputView.printMenus(cafe.getMenus());
                try {
                    menuNumber = InputView.inputMenuNumber();
                    findMenuByNumber(menuNumber);
                    cafe.orderMenu(tableNumber, menuNumber, InputView.inputMenuQuantity());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (selectedFunction == PAY) {
                OutputView.printTables(cafe.getTables());
                try {
                    tableNumber = InputView.inputTableNumber();
                    OutputView.printOrderDetails(cafe.getOrderMenusTableOf(tableNumber));
                    Payment payment = Payment.findByIndex(InputView.inputPayment(tableNumber));
                    OutputView.printFinalPrice(cafe.payOrders(tableNumber, payment));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (selectedFunction == EXIT) {
                cafe.close();
                continue;
            }
            OutputView.printFunctionNotExist();
        }
    }
}
