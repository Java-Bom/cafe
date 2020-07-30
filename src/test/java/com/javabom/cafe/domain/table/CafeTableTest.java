package com.javabom.cafe.domain.table;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.vo.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CafeTableTest {

    @Test
    @DisplayName("현재까지 주문한 모든 메뉴에 대한 가격을 반환하는지 테스트")
    void getTotalAmount() {
        CafeTable cafeTable = CafeTable.ofName("1");
        createOrderMenus(cafeTable);

        Amount totalAmount = cafeTable.getTotalAmount();

        assertThat(totalAmount).isEqualTo(Amount.valueOf(15000));
    }


    private void createOrderMenus(final CafeTable cafeTable) {
        Menu cake = new Menu("cheezeCake", MenuType.CAKE, 7000);
        Menu americano = new Menu("americano", MenuType.BEVERAGE, 4000);
        OrderMenu cakeOrder = OrderMenu.selectOrderMenu(cake, cafeTable, 1);
        OrderMenu americanoOrder1 = OrderMenu.selectOrderMenu(americano, cafeTable, 1);
        OrderMenu americanoOrder2 = OrderMenu.selectOrderMenu(americano, cafeTable, 1);
    }
}