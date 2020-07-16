package domain.discount;

import domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PercentDiscountPolicyTest {

    @DisplayName("총 금액에서 10으로 나눈 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 10, 7000"})
    void getDiscountAmount(int menuIdx, int quantity, int expected) {
        //given
        DiscountPolicy discountPolicy = new PercentDiscountPolicy();
        Menus menus = new Menus();
        menus.addMenu(menuIdx, quantity);

        //when
        int actual = discountPolicy.getDiscountAmount(menus, menus.getAmount()).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}