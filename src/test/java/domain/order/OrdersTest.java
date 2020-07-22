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

class OrdersTest {

    @DisplayName("주문을 추가한다.")
    @Test
    void add() {
        //given
        Orders orders = new Orders();
        Menu menu = MenuRepository.find(1);
        Order order = new Order(menu, Quantity.of(1));

        //when
        orders.add(order);

        //then
        assertThat(orders.get().get(0)).isEqualTo(order);
    }

    @DisplayName("주문을 추가할 때 같은 메뉴의 주문이 있으면 주문의 수량을 더한다.")
    @Test
    void addEqualOrder() {
        //given
        Orders orders = new Orders();
        Menu menu = MenuRepository.find(1);
        Order order1 = new Order(menu, Quantity.of(1));
        Order order2 = new Order(menu, Quantity.of(2));

        //when
        orders.add(order1);
        orders.add(order2);
        Order actual = orders.get().get(0);

        //then
        assertThat(actual.getQuantityBy("가나슈")).isEqualTo(3);

    }

    @DisplayName("현재 주문의 총 금액을 반환한다.")
    @Test
    void getAmount() {
        //given
        Orders orders = new Orders();
        Menu menu = MenuRepository.find(1);
        Order order = new Order(menu, Quantity.of(5));
        orders.add(order);

        //when
        int actual = orders.getAmount().get();
        int expected = menu.getPrice() * 5;

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카테고리와 일치하는 주문 수량을 반환한다.")
    @ParameterizedTest
    @CsvSource({"CAKE, 1, 10, 10", "BEVERAGE, 1, 10 ,0", "BEVERAGE, 23, 10, 10"})
    void getQuantityByCategory(Category category, int menuIdx, int quantity, int expected) {
        //given
        Orders orders = new Orders();
        Menu menu = MenuRepository.find(menuIdx);
        Order order = new Order(menu, Quantity.of(quantity));
        orders.add(order);

        //when
        int actual = orders.getQuantityBy(category).get();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}