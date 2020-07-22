package domain.discount;

import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.Orders;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AmountDiscountPolicyTest {

    @DisplayName("케이크 종류 메뉴의 수량의 합이 3개가 넘어가면 3000원씩 할인되는 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 10, 9000", "23, 10, 0", "1, 2, 0"})
    void getDiscountAmount(int menuIdx, int quantity, int expected) {
        //given
        DiscountPolicy discountPolicy = new AmountDiscountPolicy();
        Orders orders = new Orders();
        Order order = new Order(MenuRepository.find(menuIdx), Quantity.of(quantity));
        orders.add(order);

        //when
        int actual = discountPolicy.getDiscountAmount(orders, orders.getAmount()).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}