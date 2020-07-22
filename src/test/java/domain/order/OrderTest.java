package domain.order;

import domain.enums.Category;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("메뉴의 가격에 수량을 곱한값을 반환한다.")
    @Test
    void getAmount() {
        //given
        Menu menu = MenuRepository.find(1);
        Quantity quantity = Quantity.of(3);
        Order order = new Order(menu, quantity);

        //when
        int actual = order.getAmount();
        int expected = menu.getPrice() * quantity.get();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("메뉴의 카테고리와 일치하는 주문 수량을 반한다.")
    @ParameterizedTest
    @CsvSource({"CAKE, 1, 10, 10", "BEVERAGE, 1, 10 ,0", "BEVERAGE, 23, 10, 10"})
    void getQuantityByCategory(Category category, int menuIdx, int quantity, int expected) {
        //given
        Menu menu = MenuRepository.find(menuIdx);
        Order order = new Order(menu, Quantity.of(quantity));

        //when
        int actual = order.getQuantityBy(category);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("메뉴의 이름과 일치하는 주문 수량을 반환한다.")
    @ParameterizedTest
    @CsvSource({"가나슈, 1, 10, 10", "그린티 라떼, 1, 10, 0"})
    void getQuantityByName(String name, int menuIdx, int quantity, int expected) {
        //given
        Menu menu = MenuRepository.find(menuIdx);
        Order order = new Order(menu, Quantity.of(quantity));

        //when
        int actual = order.getQuantityBy(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}