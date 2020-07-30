package com.javabom.cafe;

import domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentTest {

    @DisplayName("결제 방법 인덱스에 따라 해당 결제 방법 Enum을 잘 찾는지 확인")
    @ParameterizedTest
    @CsvSource({"1,CARD", "2,CASH"})
    void findByIndex(int index, Payment payment) {
        // then
        assertThat(Payment.findByIndex(index))
                .isEqualTo(payment);
    }

    @DisplayName("결제방법 인덱스가 존재하지 않는경우 Exception")
    @ParameterizedTest
    @CsvSource({"0,3"})
    void findByIndexFail(int index) {
        // then
        assertThatThrownBy(() -> Payment.findByIndex(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("%d번 결제 수단은 존재하지 않습니다.", index));
    }

    @DisplayName("결제 방법에 따른 할인이 되는지 확인")
    @ParameterizedTest
    @CsvSource({"CARD,5000", "CASH,4500"})
    void calculateFinalPrice(Payment payment, int finalPrice) {
        // then
        assertThat(payment.calculateFinalPrice(5000))
                .isEqualTo(finalPrice);
    }
}
