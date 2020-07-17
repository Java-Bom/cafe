package domain.registration;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.OrderMenu;
import domain.table.Table;
import domain.table.TableRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    void orderTableValidTest() {
        Orders orders = new Orders();

        Table table1 = TableRepository.getTable(1);
        Table table2 = TableRepository.getTable(2);
        Menu menu = MenuRepository.getMenuByNumber(1);
        int quantity = 10;

        Order order = Order.initOrder(table1);
        order.addMenu(new OrderMenu(menu, quantity));;
        orders.addOrder(order);


        assertThatThrownBy(() -> orders.getOrderByTable(table2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 테이블 : %d - 해당 테이블은 주문한적이 없습니다.", 2);

    }
}