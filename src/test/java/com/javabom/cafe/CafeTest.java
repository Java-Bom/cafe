package com.javabom.cafe;

import domain.Cafe;
import domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeTest {

    @DisplayName("주문한 적이 없는 테이블에서 주문시 Exception")
    @Test
    void validateTableHasMenu() {
        // given
        Cafe cafe = new Cafe(Collections.emptyList(), Collections.emptyList());

        // then
        assertThatThrownBy(() -> cafe.payOrders(1, Payment.CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 테이블에는 주문한 메뉴가 없습니다.");
    }

}
