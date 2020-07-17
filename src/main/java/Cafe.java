import domain.Pos;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.OrderMenu;
import domain.payment.PaymentType;
import domain.table.Table;
import domain.table.TableRepository;
import view.InputView;
import view.OutputView;

public class Cafe {

    private static boolean isOn = true;

    private Pos pos = new Pos();

    private Cafe() {

    }

    public static void start() {
        Cafe cafe = new Cafe();
        while (isOn) {
            cafe.nextStep(InputView.inputFunction());
        }
    }

    private void nextStep(final int functionNumber) {
        if (functionNumber == 3) {
            isOn = false;
        }
        if (functionNumber == 1) {
            startOrder();
        }
        if (functionNumber == 2) {
            startPayment();
        }

    }

    private void startPayment() {
        OutputView.printTables(TableRepository.tables());

        int tableNumber = InputView.inputPaymentTableNumber();
        int paymentMethodNumber = InputView.inputPaymentMethod(tableNumber);

        PaymentType paymentMethod = getPaymentMethod(paymentMethodNumber);
        Table table = TableRepository.getTable(tableNumber);

        OutputView.printPaymentAmount(pos.getPaymentAmount(table, paymentMethod));
    }

    private PaymentType getPaymentMethod(final int paymentMethodNumber) {
        if (paymentMethodNumber == 1) {
            return PaymentType.CARD;
        }

        return PaymentType.CASH;
    }

    private void startOrder() {
        OutputView.printTables(TableRepository.tables());
        final int tableNumber = InputView.inputTableNumber();
        OutputView.printMenus(MenuRepository.menus());

        final int menuNumber = InputView.inputMenuNumber();
        final int quantity = InputView.inputQuantity();

        final Menu selectedMenu = MenuRepository.getMenuByNumber(menuNumber);
        pos.order(TableRepository.getTable(tableNumber), new OrderMenu(selectedMenu, quantity));
    }
}
