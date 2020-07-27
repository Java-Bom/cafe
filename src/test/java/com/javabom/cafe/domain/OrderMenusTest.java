package com.javabom.cafe.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenusTest {

    @DisplayName("한 메뉴가 30개를 초과하는 경우 Exception")
    @Test
    void validateSameMenuMaxCount() {
        // given
        Menu menu = new Menu(0, "name", Category.CAKE, 1);
        OrderMenus orderMenus = OrderMenus.empty();

        // then
        assertThatThrownBy(() -> orderMenus.addMenu(menu, 31))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 메뉴의 수량은 30개를 초과할 수 없습니다. 기존 메뉴수량 : 0, 새로 들어온 수량 : 31");
    }

    @DisplayName("메뉴를 추가한 경우 isNotEmpty 상태값이 바뀌는지 확인")
    @Test
    void isNotEmpty() {
        // given
        Menu menu = new Menu(0, "name", Category.CAKE, 1);
        OrderMenus orderMenus = OrderMenus.empty();
        orderMenus.addMenu(menu, 1);

        // then
        assertThat(orderMenus.isNotEmpty()).isTrue();
    }

    @DisplayName("케이크, 카드할인이 최종적으로 적용되는지 확인")
    @ParameterizedTest
    @CsvSource({"CASH,2700", "CARD,3000"})
    void getTotalPrice(Payment payment, int finalPrice) {
        // given
        Menu menu = new Menu(0, "name", Category.CAKE, 2000);
        OrderMenus orderMenus = OrderMenus.empty();
        orderMenus.addMenu(menu, 3);

        // then
        assertThat(orderMenus.getTotalPrice(payment)).isEqualTo(finalPrice);
    }
}