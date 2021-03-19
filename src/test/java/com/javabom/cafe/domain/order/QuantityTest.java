package com.javabom.cafe.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {

    @DisplayName("수량이 1보다 작으면 exception throw")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2})
    void validateQuantity(int wrongQuantity) {
        assertThatThrownBy(() -> new Quantity(wrongQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량은 1 보다 작을 수 없습니다. - " + wrongQuantity);
    }

    @DisplayName("두 quantity를 더하면 새로운 quantity 가 반환된다. ")
    @Test
    void addQuantity() {
        Quantity source = new Quantity(2);
        Quantity target = new Quantity(3);

        Quantity result = source.add(target);

        assertThat(result.getValue()).isEqualTo(5);
    }
}