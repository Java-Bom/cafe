package domain.menu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMenusTest {

    @Test
    void containsOrderMenuTest() {
        Menu menu1 = MenuRepository.getMenuByNumber(1);
        Menu menu2 = MenuRepository.getMenuByNumber(2);
        OrderMenus orderMenus = new OrderMenus();

        OrderMenu orderMenu1 = new OrderMenu(menu1, 1);
        OrderMenu previousMenu = new OrderMenu(menu1, 10);
        OrderMenu newOrderMenu = new OrderMenu(menu2, 10);

        orderMenus.addMenu(orderMenu1);

        assertThat(orderMenus.containsOrderMenu(previousMenu)).isTrue();
        assertThat(orderMenus.containsOrderMenu(newOrderMenu)).isFalse();
    }
}