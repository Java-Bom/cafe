package domain;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.OrderMenu;
import domain.payment.PaymentType;
import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Amount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PosTest {

    @Test
    void getPaymentAmountTest() {
        Pos pos = new Pos();
        Table table1 = TableRepository.getTable(1);
        Table table2 = TableRepository.getTable(2);

        Menu menu = MenuRepository.getMenuByNumber(1);
        int quantity = 10;
        OrderMenu orderMenu = new OrderMenu(menu, quantity);
        OrderMenu orderMenu2 = new OrderMenu(menu, quantity);

        pos.order(table1, orderMenu);
        pos.order(table1, orderMenu);

        pos.order(table2, orderMenu2);
        pos.order(table2, orderMenu2);

        Amount paymentAmount = menu.getAmount().multiplyValue(20);
        Amount discountAmount = Amount.valueOf(18000);
        paymentAmount = paymentAmount.subtractionAmount(discountAmount);
        Amount cashAmount = paymentAmount.multiplyValue(PaymentType.CASH.getDiscountRate());

        assertThat(pos.getPaymentAmount(table1, PaymentType.CARD)).isEqualTo(paymentAmount);
        assertThat(pos.getPaymentAmount(table2, PaymentType.CASH)).isEqualTo(cashAmount);
    }
}