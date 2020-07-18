package cafe.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderRepositoryTest {

    cafe.domain.OrderRepository orderRepository = new cafe.domain.OrderRepository();

    @BeforeEach
    void init() {
        orderRepository.addOrder(new cafe.domain.Order(1, 1));
        orderRepository.addOrder(new cafe.domain.Order(2, 1));
        orderRepository.addOrder(new cafe.domain.Order(3, 2));
        orderRepository.addOrder(new cafe.domain.Order(3, 1));
    }

    @DisplayName("주문레포지토리에서 해당 테이블에 해당하는 주문만 뽑는다")
    @Test
    void getOrderByTable() {
        List<cafe.domain.Order> tableOne = orderRepository.findByTableNumber(1);
        List<cafe.domain.Order> tableTwo = orderRepository.findByTableNumber(2);

        assertAll(
                () -> assertThat(tableOne).contains(new cafe.domain.Order(1, 1), new cafe.domain.Order(2, 1)),
                () -> assertThat(tableTwo).contains(new cafe.domain.Order(3, 2))
        );
    }

    @DisplayName("한 테이블에 주문 갯수가 30이 넘어가면 Exception")
    @Test
    void underThirty() {
        cafe.domain.OrderRepository failRepo = new cafe.domain.OrderRepository();
        fillThirty(failRepo);

        assertThatThrownBy(() -> failRepo.addOrder(new cafe.domain.Order(2, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("Max Order Counts Of Per Table is %d", 30));
    }

    @DisplayName("해당 테이블 넘버에 해당하는 주문을 모두 삭제한다")
    @Test
    void delete() {
        orderRepository.resolveByTableNumber(1);

        assertThat(orderRepository.findByTableNumber(1).size()).isEqualTo(0);
    }

    private void fillThirty(final cafe.domain.OrderRepository failRepo) {
        for (int i = 0; i < 5; i++) {
            failRepo.addOrder(new cafe.domain.Order(1, 1));
            failRepo.addOrder(new cafe.domain.Order(2, 1));
            failRepo.addOrder(new cafe.domain.Order(3, 1));
            failRepo.addOrder(new cafe.domain.Order(4, 1));
            failRepo.addOrder(new cafe.domain.Order(5, 1));
            failRepo.addOrder(new cafe.domain.Order(6, 1));
        }
    }
}