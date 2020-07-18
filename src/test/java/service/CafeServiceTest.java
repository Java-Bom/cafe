package service;

import domain.Bill;
import domain.OrderRepository;
import domain.PayType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CafeServiceTest {

    @DisplayName("메뉴숫자, 수량, 테이블넘버로 주문한다")
    @Test
    void order() {
        OrderRepository orderRepository = new OrderRepository();
        CafeService cafeService = new CafeService(orderRepository);

        cafeService.orderMenu(1, 3, 1);

        assertThat(orderRepository.findByTableNumber(1).size()).isEqualTo(3);
    }

    @DisplayName("해당 테이블 넘버에 대한 계산서를 반환한다.")
    @Test
    void bill() {
        OrderRepository orderRepository = new OrderRepository();
        CafeService cafeService = new CafeService(orderRepository);
        cafeService.orderMenu(1, 3, 1);
        cafeService.orderMenu(1, 3, 1);
        cafeService.orderMenu(2, 3, 1);

        Bill bill = cafeService.findBillsByTable(1);

        assertThat(bill.getBillTemplates()).contains(new Bill.BillTemplate("가나슈", 6L, 42000L));
    }

    @DisplayName("해당 테이블넘버에 대한 계산 금액을 반환한다. - 카드는 할인없음, 현금은 0.9, 케이크는 3개마다 3000원 할인")
    @CsvSource(value = {"CARD, 36000", "CASH, 32400"})
    @ParameterizedTest
    void calculate(PayType payType, Long price) {
        OrderRepository orderRepository = new OrderRepository();
        CafeService cafeService = new CafeService(orderRepository);
        cafeService.orderMenu(1, 3, 1);
        cafeService.orderMenu(1, 3, 1);

        long result = cafeService.calculate(1, payType);

        assertThat(result).isEqualTo(price);
    }

}