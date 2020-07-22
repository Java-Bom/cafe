package domain.discount;

import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.Orders;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PercentDiscountPolicyTest {

    @DisplayName("총 금액에서 10으로 나눈 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 10, 7000"})
    void getDiscountAmount(int menuIdx, int quantity, int expected) {
        //given
        DiscountPolicy discountPolicy = new PercentDiscountPolicy();
        Orders orders = new Orders();
        Order order = new Order(MenuRepository.find(menuIdx), Quantity.of(quantity));
        orders.add(order);

        //when
        int actual = discountPolicy.getDiscountAmount(orders, orders.getAmount()).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}