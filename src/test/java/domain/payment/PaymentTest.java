package domain.payment;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.OrderMenu;
import domain.vo.Amount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void quantityDiscountPaymentTest() {
        Payment payment = new Payment();
        List<OrderMenu> orderMenus = new ArrayList<>();
        Menu menu1 = MenuRepository.getMenuByNumber(1);
        OrderMenu orderMenu1 = new OrderMenu(menu1, 3);
        orderMenus.add(orderMenu1);


        Amount paymentAmount = payment.getPaymentAmount(orderMenus, PaymentType.CARD);
        Amount amount = menu1.getAmount().multiplyValue(3);

        assertThat(paymentAmount).isEqualTo(amount.subtractionAmount(Amount.valueOf(3_000)));
    }


    @Test
    public void paymentTypeDiscountTest() {
        Payment payment = new Payment();
        List<OrderMenu> orderMenus = new ArrayList<>();
        Menu menu1 = MenuRepository.getMenuByNumber(1);
        OrderMenu orderMenu1 = new OrderMenu(menu1, 1);
        orderMenus.add(orderMenu1);


        Amount paymentAmount = payment.getPaymentAmount(orderMenus, PaymentType.CASH);
        Amount amount = menu1.getAmount();

        assertThat(paymentAmount).isEqualTo(amount.multiplyValue(PaymentType.CASH.getDiscountRate()));
    }


    @Test
    public void doubleDiscountTest() {
        Payment payment = new Payment();
        List<OrderMenu> orderMenus = new ArrayList<>();
        Menu menu1 = MenuRepository.getMenuByNumber(1);
        OrderMenu orderMenu1 = new OrderMenu(menu1, 3);
        orderMenus.add(orderMenu1);


        Amount paymentAmount = payment.getPaymentAmount(orderMenus, PaymentType.CASH);
        Amount amount = menu1.getAmount().multiplyValue(3);
        Amount quantityDiscountAmount = amount.subtractionAmount(Amount.valueOf(3_000));
        Amount discountAmount = quantityDiscountAmount.multiplyValue(PaymentType.CASH.getDiscountRate());

        assertThat(paymentAmount).isEqualTo(discountAmount);
    }
}