package com.javabom.cafe.view;//package com.javabom.cafe;
//
//import com.javabom.cafe.domain.menu.Menu;
//import com.javabom.cafe.repository.MenuRepository;
//import com.javabom.cafe.domain.menu.OrderMenu;
//import com.javabom.cafe.domain.payment.DiscountConditions;
//import com.javabom.cafe.domain.payment.PaymentType;
//import com.javabom.cafe.domain.order.Order;
//import com.javabom.cafe.domain.order.Orders;
//import com.javabom.cafe.domain.table.Table;
//import com.javabom.cafe.repository.TableRepository;
//import com.javabom.cafe.domain.vo.Quantity;
//import com.javabom.cafe.view.InputView;
//import com.javabom.cafe.view.OutputView;
//
//public class Cafe {
//
//    private static boolean isOn = true;
//
//    private final Orders orders = new Orders();
//
//    public static void start() {
//        Cafe com.javabom.cafe = new Cafe();
//        while (isOn) {
//            com.javabom.cafe.nextStep(InputView.inputFunction());
//        }
//    }
//
//    private void nextStep(final int functionNumber) {
//        if (functionNumber == 3) {
//            isOn = false;
//        }
//        if (functionNumber == 1) {
//            startOrder();
//        }
//        if (functionNumber == 2) {
//            startPayment();
//        }
//
//    }
//
//    private void startOrder() {
//        OutputView.printTables(TableRepository.tables());
//        final Table tableNumber = TableRepository.getTable(InputView.inputTableNumber());
//        OutputView.printMenus(MenuRepository.menus());
//
//        final Menu menu = MenuRepository.getMenuByNumber(InputView.inputMenuNumber());
//        final Quantity quantity = Quantity.valueOf(InputView.inputQuantity());
//        final OrderMenu orderMenu = new OrderMenu(menu, quantity);
//        orders.addOrder(tableNumber, orderMenu);
//    }
//
//    private void startPayment() {
//        OutputView.printTables(TableRepository.tables());
//
//        int tableNumber = InputView.inputPaymentTableNumber();
//        final Order order = orders.findByTableNumber(tableNumber);
//        int paymentMethodNumber = InputView.inputPaymentMethod(tableNumber);
//
//        PaymentType paymentMethod = PaymentType.findByNumber(paymentMethodNumber);
//        DiscountConditions discountConditions = paymentMethod.getDiscountConditions();
//
//        OutputView.printPaymentAmount(discountConditions.getDiscountAmount(order));
//        orders.remove(order);
//    }
//}
