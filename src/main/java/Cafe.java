import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.OrderMenu;
import domain.payment.DiscountConditions;
import domain.payment.PaymentType;
import domain.registration.Order;
import domain.registration.Orders;
import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Quantity;
import view.InputView;
import view.OutputView;

public class Cafe {

    private static boolean isOn = true;

    private final Orders orders = new Orders();

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

    private void startOrder() {
        OutputView.printTables(TableRepository.tables());
        final Table tableNumber = TableRepository.getTable(InputView.inputTableNumber());
        OutputView.printMenus(MenuRepository.menus());

        final Menu menu = MenuRepository.getMenuByNumber(InputView.inputMenuNumber());
        final Quantity quantity = Quantity.valueOf(InputView.inputQuantity());
        final OrderMenu orderMenu = new OrderMenu(menu, quantity);
        orders.addOrder(tableNumber, orderMenu);
    }

    private void startPayment() {
        OutputView.printTables(TableRepository.tables());

        int tableNumber = InputView.inputPaymentTableNumber();
        final Order order = orders.findByTableNumber(tableNumber);
        int paymentMethodNumber = InputView.inputPaymentMethod(tableNumber);

        PaymentType paymentMethod = PaymentType.findByNumber(paymentMethodNumber);
        DiscountConditions discountConditions = paymentMethod.getDiscountConditions();

        OutputView.printPaymentAmount(discountConditions.getDiscountAmount(order));
        orders.remove(order);
    }
}
