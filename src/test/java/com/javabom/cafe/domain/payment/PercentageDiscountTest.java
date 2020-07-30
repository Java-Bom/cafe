package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PercentageDiscountTest {

    @Test
    @DisplayName("할인 비율만큼 할인 되는지 확인.")
    void getDiscountedAmount() {
        PercentageDiscount percentageDiscount = new PercentageDiscount(0.9);
        List<OrderMenu> orders = getOrders();

        Amount totalAmount = Amount.valueOf(15000);
        Amount discountedAmount = percentageDiscount.getDiscountedAmount(orders, totalAmount);


        assertThat(discountedAmount).isEqualTo(Amount.valueOf(13500));
    }

    private List<OrderMenu> getOrders() {
        CafeTable cafeTable = CafeTable.ofName("1");
        Menu cake = new Menu("cheezeCake", MenuType.CAKE, 7000);
        Menu americano = new Menu("americano", MenuType.BEVERAGE, 4000);

        OrderMenu cakeOrder = OrderMenu.selectOrderMenu(cake, cafeTable, 1);
        OrderMenu americanoOrder1 = OrderMenu.selectOrderMenu(americano, cafeTable, 1);
        OrderMenu americanoOrder2 = OrderMenu.selectOrderMenu(americano, cafeTable, 1);

        return Arrays.asList(cakeOrder, americanoOrder1, americanoOrder2);
    }
}