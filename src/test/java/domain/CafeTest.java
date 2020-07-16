package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CafeTest {

    @DisplayName("주문한 적이 없는 테이블에서 주문시 Exception")
    @Test
    void validateTableHasMenu() {
        // given
        Cafe cafe = new Cafe();

        // then
        assertThatThrownBy(() -> cafe.payOrders(1, Payment.CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 테이블에는 주문한 메뉴가 없습니다.");
    }

    @DisplayName("주문후 결제시 마지막 금액이 제대로 나오는지 확인")
    @Test
    void payOrders() {
        // given
        Cafe cafe = new Cafe();
        cafe.orderMenu(1, 1, 1);

        // when
        int actualPrice = cafe.payOrders(1, Payment.CASH);

        // then
        assertThat(actualPrice).isEqualTo(6300);
    }
}