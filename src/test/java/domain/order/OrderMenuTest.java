package domain.order;

import domain.menu.Category;
import domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuTest {

    @DisplayName("같은 메뉴 주문 수량이 30개를 초과하면 exception throw")
    @Test
    void overMaxQuantityThrowException() {
        Menu menu = Menu.builder()
                .number(1)
                .name("치즈 케잌")
                .price(3000)
                .category(Category.CAKE)
                .build();

        assertThatThrownBy(() -> new OrderMenu(menu, new Quantity(31)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 가능 수량을 초과 하였습니다. - " + 31 + "개");
    }

    @DisplayName("메뉴 수량을 추가할때 30개를 초과하면 exception throw")
    @Test
    void addQuantityThrowException() {
        Menu menu = Menu.builder()
                .number(1)
                .name("치즈 케잌")
                .price(3000)
                .category(Category.CAKE)
                .build();

        OrderMenu orderMenu = new OrderMenu(menu, new Quantity(29));

        assertThatThrownBy(() -> orderMenu.addQuantity(new Quantity(2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 가능 수량을 초과 하였습니다. - " + 31 + "개");

    }
}