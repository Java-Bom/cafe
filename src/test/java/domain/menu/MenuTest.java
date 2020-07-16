package domain.menu;

import domain.enums.Category;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @DisplayName("메뉴의 가격에 수량을 곱한 값을 반환한다.")
    @Test
    void getMultiplyPriceOf() {
        //given
        Menu menu = MenuRepository.find(1);
        Quantity quantity = Quantity.of(5);

        //when
        int actual = menu.getMultiplyPriceOf(quantity);

        //then
        assertThat(actual).isEqualTo(35_000);
    }

    @DisplayName("메뉴의 카테고리와 일치하면 true, 다르면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, CAKE, true", "1, BEVERAGE, false"})
    void equalsOfByCategory(int menuIdx, Category category, boolean expected) {
        //given
        Menu menu = MenuRepository.find(menuIdx);

        //then
        assertThat(menu.equalsOf(category)).isEqualTo(expected);
    }

    @DisplayName("메뉴의 이름과 일치하면 true, 다르면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 1, true", "1, 2, false"})
    void equalsOfByNumber(int menuIdx, int number, boolean expected) {
        //given
        Menu menu = MenuRepository.find(menuIdx);

        //then
        assertThat(menu.equalsOf(number)).isEqualTo(expected);
    }
}