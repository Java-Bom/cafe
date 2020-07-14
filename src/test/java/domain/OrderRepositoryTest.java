package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class OrderRepositoryTest {

    OrderRepository orderRepository = new OrderRepository();

    @BeforeEach
    void init() {
        orderRepository.addOrder(new Order(1, 1));
        orderRepository.addOrder(new Order(2, 1));
        orderRepository.addOrder(new Order(3, 2));
        orderRepository.addOrder(new Order(3, 1));
    }

    @DisplayName("주문레포지토리에서 해당 테이블에 해당하는 주문만 뽑는다")
    @Test
    void getOrderByTable() {
        List<Order> byTable = orderRepository.findByTable(new Table(1));
    }
}