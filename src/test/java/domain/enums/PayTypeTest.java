package domain.enums;

import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.Orders;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PayTypeTest {

    @DisplayName("결제 수단이 1이면 CARD, 2면 CASH를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, CARD", "2, CASH"})
    void findByType(int paymentMethod, PayType expected) {
        //when
        PayType actual = PayType.findByType(paymentMethod);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("존재 하지 않는 결제 수단이면 NoSuchElementException을 발생시킨다.")
    @Test
    void findByTypeError(){
        //given
        int paymentMethod = 3;

        //then
        assertThatThrownBy(() -> PayType.findByType(paymentMethod))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("결제수단: %d, 존재하지 않는 결제수단입니다.", paymentMethod));
    }

    @DisplayName("결제 수단이 1이면 수량 할인된 금액을, 2면 수량과 퍼센트 할인된 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({"CARD, 18000", "CASH, 16200"})
    void calculateDiscountFee(PayType payType, int expected) {
        //given
        Orders orders = new Orders();
        Order order = new Order(MenuRepository.find(1), Quantity.of(3));
        orders.add(order);

        //when
        int actual = payType.calculateDiscountFee(orders).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}