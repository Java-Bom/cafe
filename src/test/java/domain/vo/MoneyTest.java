package domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @DisplayName("금액이 0보다 작으면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkNegative() {
        //given
        int fee = -1;

        //then
        assertThatThrownBy(() -> Money.of(fee))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("금액:%d, 금액은 0이상 입니다.", fee));
    }

    @DisplayName("나누는 숫자가 0이면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkDivideValue() {
        //given
        Money money = Money.of(1000);
        int number = 0;

        //then
        assertThatThrownBy(() -> money.divide(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("숫자:%d, 0이 들어오면 안됩니다.", number));
    }
}
