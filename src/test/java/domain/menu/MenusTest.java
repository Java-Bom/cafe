package domain.menu;

import domain.enums.Category;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenusTest {

    @DisplayName("들어온 주무 수량이 음수면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkNegativeQuantity() {
        //given
        Menus menus = new Menus();
        int menuIdx = 1;
        int quantity = -1;

        //then
        assertThatThrownBy(() -> menus.addMenu(menuIdx, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("주문 수량: %d, 최소 1개 이상의 주문을 하셔야 합니다.", quantity));
    }

    @DisplayName("메뉴와 수량이 추가되었는지 확인한다.")
    @Test
    void addMenu() {
        //given
        Menus menus = new Menus();
        int menuIdx = 1;
        int quantity = 5;

        //when
        menus.addMenu(menuIdx, quantity);
        Map<Menu, Quantity> actual = menus.get();
        Menu menu = MenuRepository.find(menuIdx);

        //then
        assertThat(actual.containsKey(menu)).isTrue();
        assertThat(actual.get(menu).get()).isEqualTo(quantity);
    }

    @DisplayName("주문한 메뉴들의 총 금액을 확인한다.")
    @ParameterizedTest
    @CsvSource({"1,4,28000"})
    void getAmount(int menuIdx, int quantity, int expected) {
        //given
        Menus menus = new Menus();
        menus.addMenu(menuIdx, quantity);

        //when
        int actual = menus.getAmount().get();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카테고리별 주문 수량을 확인한다.")
    @ParameterizedTest
    @CsvSource({"CAKE, 1, 5", "BEVERAGE, 23, 10"})
    void getQuantityByCategory(Category category, int menuIdx, int quantity) {
        //given
        Menus menus = new Menus();
        menus.addMenu(menuIdx, quantity);

        //when
        int actual = menus.getQuantityByCategory(category).get();

        //then
        assertThat(actual).isEqualTo(quantity);
    }
}