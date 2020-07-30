package com.javabom.cafe.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {

    @ParameterizedTest
    @DisplayName("수량이 0미만 30초과이면 예외처리하는지 테스트.")
    @ValueSource(ints = {-1, 31})
    void validQuantityRangeTest(int value) {
        assertThatThrownBy(() -> Quantity.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("입력한 수량 : %d - 최대 30개까지 주문할 수 있습니다.", value));
    }

    @Test
    @DisplayName("입력 받은 Quantity들의 value을 모두 더하는지 테스트.")
    void sum() {
        List<Quantity> quantities = Arrays.asList(Quantity.valueOf(10), Quantity.valueOf(2));

        Quantity totalQuantity = Quantity.sum(quantities);

        assertThat(totalQuantity).isEqualTo(Quantity.valueOf(12));
    }

    @Test
    @DisplayName("입력 받은 value만큼 나눈 결과를 반환하는지 테스트.")
    void divideQuantity() {
        Quantity quantity = Quantity.valueOf(10);

        Quantity divideQuantity = quantity.divideQuantity(Quantity.valueOf(2));

        assertThat(divideQuantity).isEqualTo(Quantity.valueOf(5));
    }
}