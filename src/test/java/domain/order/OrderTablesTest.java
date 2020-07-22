package domain.order;

import domain.menu.MenuRepository;
import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrderTablesTest {

    @DisplayName("테이블에 주문 기록이 없으면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkTableEmpty() {
        //given
        OrderTables orderTables = new OrderTables();
        int tableNumber = 1;
        int paymentMethod = 1;

        //then
        assertThatThrownBy(() -> orderTables.calculateFee(tableNumber, paymentMethod))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("테이블 번호: %s, 주문한 기록이 없습니다.", tableNumber));
    }

    @DisplayName("일치하는 테이블의 주문 목록을 반환한다.")
    @Test
    void findOrdersBy() {
        //given
        OrderTables orderTables = new OrderTables();
        Table table = TableRepository.find(1);
        Order order = new Order(MenuRepository.find(1), Quantity.of(5));
        orderTables.addOrderTable(order, table);

        //when
        List<Order> actual = orderTables.findOrdersBy(table);

        //then
        assertThat(actual.contains(order)).isTrue();
    }
}