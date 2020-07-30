package com.javabom.cafe.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    @Test
    @DisplayName("입력된 Amount들의 value을 모두 더한 Amount 객체 반환하는지 테스트")
    void sum() {
        List<Amount> amounts = Arrays.asList(Amount.valueOf(10), Amount.valueOf(30));
        Amount amount = Amount.valueOf(40);

        Amount totalAmount = Amount.sum(amounts);

        assertThat(totalAmount).isEqualTo(amount);
    }

    @Test
    @DisplayName("입력된 Amount 만큼 value을 차감하는지 테스트")
    void subtractionAmountTest() {
        Amount amount = Amount.valueOf(30);

        Amount remainAmount = amount.subtractionAmount(Amount.valueOf(10));

        assertThat(remainAmount).isEqualTo(Amount.valueOf(20));
    }

    @Test
    @DisplayName("입력된 Amount 만큼 value을 곱하는지 테스트")
    void multiplyTest() {
        Amount amount = Amount.valueOf(10);

        Amount resultAmount = amount.multiplyValue(10);

        assertThat(resultAmount).isEqualTo(Amount.valueOf(100));
    }

}