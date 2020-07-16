package domain.discount;

import domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmountDiscountPolicyTest {

    @DisplayName("케이크 종류 메뉴의 수량의 합이 3개가 넘어가면 3000원씩 할인되는 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 10, 9000", "23, 10, 0", "1, 2, 0"})
    void getDiscountAmount(int menuIdx, int quantity, int expected) {
        //given
        DiscountPolicy discountPolicy = new AmountDiscountPolicy();
        Menus menus = new Menus();
        menus.addMenu(menuIdx, quantity);

        //when
        int actual = discountPolicy.getDiscountAmount(menus, menus.getAmount()).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}