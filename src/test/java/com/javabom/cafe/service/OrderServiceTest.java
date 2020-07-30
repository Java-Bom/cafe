package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.repository.CafeMenuRepository;
import com.javabom.cafe.repository.CafeTableRepository;
import com.javabom.cafe.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CafeMenuRepository menuRepository;

    @Autowired
    CafeTableRepository tableRepository;

    @Test
    @DisplayName("새로운 주문 생성되는지 확인.")
    void createOrderTest() {
        CafeTable cafeTable = CafeTable.ofName("1");
        tableRepository.save(cafeTable);

        Menu menu = new Menu("choco", MenuType.CAKE,7000);
        menuRepository.save(menu);

        OrderMenu orderMenu = OrderMenu.selectOrderMenu(menu, cafeTable, 3);
        orderRepository.save(orderMenu);

        OrderMenu findOrderMenu = orderRepository.findById(orderMenu.getId()).get();

        assertThat(findOrderMenu.getId()).isEqualTo(orderMenu.getId());
    }

    @Test
    @DisplayName("주문한 메뉴 목록을 모두 조회하는지 확인.")
    void getOrderMenusTest() {
        createOrders();

        List<OrderMenu> orderMenus = orderRepository.findAll();

        assertThat(orderMenus.size()).isEqualTo(2);
    }

    private void createOrders() {
        CafeTable cafeTable = CafeTable.ofName("1");
        tableRepository.save(cafeTable);

        Menu menu = new Menu("choco", MenuType.CAKE,7000);
        menuRepository.save(menu);

        OrderMenu orderMenu1 = OrderMenu.selectOrderMenu(menu, cafeTable, 3);
        OrderMenu orderMenu2 = OrderMenu.selectOrderMenu(menu, cafeTable, 2);
        orderRepository.save(orderMenu1);
        orderRepository.save(orderMenu2);
    }
}