package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @DisplayName("메뉴를 추가했을 때 상태값이 바뀌는지 확인")
    @Test
    void hasMenu() {
        // given
        Table table = new Table(0);
        Menu menu = new Menu(0, "name", Category.CAKE, 1000);

        // when
        table.addMenu(menu, 1);

        // then
        assertThat(table.hasMenu()).isTrue();
    }

    @DisplayName("추가한 메뉴에 대한 OrderDetail이 잘 만들어지는지 확인")
    @Test
    void getOrderDetails() {
        // given
        Table table = new Table(0);
        Menu menu = new Menu(0, "name", Category.CAKE, 1000);
        OrderDetail expectedOrderDetail = new OrderDetail(menu, 5);

        // when
        table.addMenu(menu, 5);
        OrderDetail actualOrderDetail = table.getOrderDetails().get(0);

        // then
        assertThat(actualOrderDetail.toString())
                .isEqualTo(expectedOrderDetail.toString());
    }

    @DisplayName("결제 방법에 따른 금액 할인이 되는지 확인")
    @CsvSource({"CASH,900", "CARD,1000"})
    @ParameterizedTest
    void getTotalPrice(Payment payment, int finalPrice) {
        // given
        Table table = new Table(0);
        Menu menu = new Menu(0, "name", Category.CAKE, 1000);

        // when
        table.addMenu(menu, 1);

        // then
        assertThat(table.getTotalPrice(payment)).isEqualTo(finalPrice);
    }
}