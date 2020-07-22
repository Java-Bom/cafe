package domain;

import domain.menu.MenuRepository;
import domain.order.Order;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class POSTest {

    @DisplayName("테이블에 주문이 들어왔는지 확인한다.")
    @Test
    void takeOrder() {
        //given
        POS pos = new POS();
        int tableNumber = 1;
        int menuIdx = 1;
        int quantity = 10;

        //when
        pos.takeOrder(tableNumber, menuIdx, quantity);
        List<Order> actual = pos.findOrderHistoryOf(tableNumber);
        Order expected = new Order(MenuRepository.find(menuIdx), Quantity.of(quantity));

        //then
        assertThat(actual.contains(expected)).isTrue();
    }

    @DisplayName("테이블에 메뉴를 주문한 적이 없으면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkMenuEmpty() {
        //given
        POS pos = new POS();
        int tableNumber = 1;
        int paymentMethod = 1;

        //then
        assertThatThrownBy(() -> pos.calculateFee(tableNumber, paymentMethod))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("테이블 번호: %d, 주문한 기록이 없습니다.", tableNumber));
    }

    @DisplayName("결제 수단에 따른 최종 결제 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1, 61000", "2, 54900"})
    void calculateFee(int paymentMethod, int expected) {
        //given
        POS pos = new POS();
        int tableNumber = 1, menuIdx = 1, quantity = 10;
        pos.takeOrder(tableNumber, menuIdx, quantity);

        //when
        int actual = pos.calculateFee(tableNumber, paymentMethod).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("테이블의 주문을 삭제한다.")
    @Test
    void clearOf() {
        //given
        POS pos = new POS();
        int tableNumber = 1;
        int menuIdx = 1;
        int quantity = 10;
        pos.takeOrder(tableNumber, menuIdx, quantity);

        //when
        pos.clearOf(tableNumber);
        List<Order> actual = pos.findOrderHistoryOf(tableNumber);

        //then
        assertThat(actual.isEmpty()).isTrue();
    }
}