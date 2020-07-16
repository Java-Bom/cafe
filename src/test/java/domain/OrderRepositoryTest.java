package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        List<Order> tableOne = orderRepository.findByTable(1);
        List<Order> tableTwo = orderRepository.findByTable(2);

        assertAll(
                () -> assertThat(tableOne).contains(new Order(1, 1), new Order(2, 1)),
                () -> assertThat(tableTwo).contains(new Order(3, 2))
        );
    }

    @DisplayName("한 테이블에 주문 갯수가 30이 넘어가면 Exception")
    @Test
    void underThirty() {
        OrderRepository failRepo = new OrderRepository();
        fillThirty(failRepo);

        assertThatThrownBy(() -> failRepo.addOrder(new Order(2, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("Max Order Counts Of Per Table is %d", 30));
    }

    private void fillThirty(final OrderRepository failRepo) {
        for (int i = 0; i < 5; i++) {
            failRepo.addOrder(new Order(1, 1));
            failRepo.addOrder(new Order(2, 1));
            failRepo.addOrder(new Order(3, 1));
            failRepo.addOrder(new Order(4, 1));
            failRepo.addOrder(new Order(5, 1));
            failRepo.addOrder(new Order(6, 1));
        }
    }
}