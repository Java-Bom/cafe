package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.payment.Payment;
import com.javabom.cafe.domain.payment.PaymentType;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.repository.CafeMenuRepository;
import com.javabom.cafe.repository.CafeTableRepository;
import com.javabom.cafe.repository.OrderRepository;
import com.javabom.cafe.repository.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private CafeTableRepository tableRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CafeMenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("주한 내역을 카드로 결제를 진행하는지 확인.")
    void payByCardTest() {
        CafeTable cafeTable = CafeTable.ofName("1");
        tableRepository.save(cafeTable);

        Menu menu = new Menu("choco", MenuType.CAKE,7000);
        menuRepository.save(menu);

        OrderMenu orderMenu1 = OrderMenu.selectOrderMenu(menu, cafeTable, 3);
        orderRepository.save(orderMenu1);

        PaymentType card = PaymentType.CARD;

        Payment payment = new Payment(cafeTable, card, card.getPaymentAmount(cafeTable));
        paymentRepository.save(payment);
        Payment findPayment = paymentRepository.findById(payment.getId()).get();

        assertThat(findPayment.getPaymentAmount()).isEqualTo(Amount.valueOf(18000d));
    }

    private void createPayments() {
        CafeTable cafeTable = CafeTable.ofName("1");
        CafeTable cafeTable2 = CafeTable.ofName("2");
        tableRepository.save(cafeTable);
        tableRepository.save(cafeTable2);

        Menu menu = new Menu("choco", MenuType.CAKE,7000);
        menuRepository.save(menu);

        OrderMenu orderMenu1 = OrderMenu.selectOrderMenu(menu, cafeTable, 3);
        orderRepository.save(orderMenu1);

        PaymentType card = PaymentType.CARD;
        PaymentType cash = PaymentType.CASH;

        Payment cardPayment = new Payment(cafeTable, card, card.getPaymentAmount(cafeTable));
        Payment cashPayment = new Payment(cafeTable2, cash, cash.getPaymentAmount(cafeTable));

        paymentRepository.save(cardPayment);
        paymentRepository.save(cashPayment);
    }

    @Test
    @DisplayName("주문한 결제 내역 모두 조회.")
    void getPaymentListTest() {
        createPayments();

        List<Payment> payments = paymentRepository.findAll();

        assertThat(payments.size()).isEqualTo(2);
    }

    @Test
    void getPaymentPriceTest() {
        CafeTable cafeTable = CafeTable.ofName("1");
        CafeTable cafeTable2 = CafeTable.ofName("2");
        tableRepository.save(cafeTable);
        tableRepository.save(cafeTable2);

        Menu menu = new Menu("choco", MenuType.CAKE,7000);
        menuRepository.save(menu);

        OrderMenu orderMenu1 = OrderMenu.selectOrderMenu(menu, cafeTable, 3);
        orderRepository.save(orderMenu1);

        PaymentType card = PaymentType.CARD;
        PaymentType cash = PaymentType.CASH;

        Payment cardPayment = new Payment(cafeTable, card, card.getPaymentAmount(cafeTable));
        Payment cashPayment = new Payment(cafeTable2, cash, cash.getPaymentAmount(cafeTable));

        assertThat(cardPayment.getPaymentAmount()).isEqualTo(Amount.valueOf(18000));
        assertThat(cashPayment.getPaymentAmount()).isEqualTo(Amount.valueOf(16200));
    }
}