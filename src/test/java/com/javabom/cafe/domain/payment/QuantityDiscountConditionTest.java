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

class QuantityDiscountConditionTest {

    @Test
    @DisplayName("CAKE에 해당 하는 메뉴만, 수량 3개당 3000원씩 할인이 적용 되는지 확인.")
    void getDiscountedAmountByCake() {
        QuantityDiscountCondition quantityDiscountCondition = new QuantityDiscountCondition();
        List<OrderMenu> cakeMenuOrders = getCakeMenuOrders();
        List<OrderMenu> beverageMenuOrders = getBeverageMenuOrders();

        Amount cakeTotalAmount = Amount.valueOf(21000);
        Amount discountedCakeAmount = quantityDiscountCondition.getDiscountedAmount(cakeMenuOrders, cakeTotalAmount);

        Amount beverageTotalAmount = Amount.valueOf(12000);
        Amount discountedBeverageAmount =
                quantityDiscountCondition.getDiscountedAmount(beverageMenuOrders, beverageTotalAmount);

        assertThat(discountedCakeAmount).isEqualTo(Amount.valueOf(18000));
        assertThat(discountedBeverageAmount).isEqualTo(Amount.valueOf(12000));
    }

    private List<OrderMenu> getCakeMenuOrders() {
        CafeTable cafeTable = CafeTable.ofName("1");
        Menu cake = new Menu("cheezeCake", MenuType.CAKE, 7000);

        OrderMenu cakeOrder = OrderMenu.selectOrderMenu(cake, cafeTable, 3);

        return Arrays.asList(cakeOrder);
    }

    private List<OrderMenu> getBeverageMenuOrders() {
        CafeTable cafeTable = CafeTable.ofName("1");
        Menu americano = new Menu("americano", MenuType.BEVERAGE, 4000);

        OrderMenu americanoOrder = OrderMenu.selectOrderMenu(americano, cafeTable, 3);

        return Arrays.asList(americanoOrder);
    }
}