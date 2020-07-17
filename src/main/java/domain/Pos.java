package domain;

import domain.menu.OrderMenu;
import domain.payment.Payment;
import domain.payment.PaymentType;
import domain.registration.Order;
import domain.registration.Orders;
import domain.table.Table;
import domain.table.Tables;
import domain.vo.Amount;

public class Pos {

    private final Orders orders;

    private final Tables orderTables;

    private final Payment payment;

    public Pos() {
        orders = new Orders();
        orderTables = new Tables();
        payment = new Payment();
    }

    public void order(final Table table, final OrderMenu orderMenu) {
        if (orderTables.contains(table)) {
            extraOrder(table, orderMenu);
            return;
        }

        initOrder(table, orderMenu);
    }

    private void initOrder(final Table table, final OrderMenu orderMenu) {
        orderTables.addTable(table);
        Order order = Order.initOrder(table);
        order.addMenu(orderMenu);
        orders.addOrder(order);
    }

    private void extraOrder(final Table table, final OrderMenu orderMenu) {
        Order orderByTable = orders.getOrderByTable(table);
        orderByTable.addMenu(orderMenu);
    }


    public Amount getPaymentAmount(final Table table, final PaymentType paymentType) {
        if (!orderTables.contains(table)) {
            throw new IllegalArgumentException(String.format("결제할 테이블 : %d - 해당 테이블은 주문한 내역이 없습니다.", table.getNumber()));
        }
        Order orderByTable = orders.getOrderByTable(table);

        return payment.getPaymentAmount(orderByTable.getOrderMenus(), paymentType);
    }
}
