package com.javabom.cafe.domain.order;

import java.util.List;

public class Payment {
    private static final int CAKE_DISCOUNT = 3000;
    private static final int CAKE_ORDER_COUNT = 3;
    private static final double CASH_DISCOUNT_RATE = 0.1;

    public static double calculateCash(List<OrderMenu> cakeOrders, List<OrderMenu> beverageOrders) {
        int priceAmount = calculatePrice(cakeOrders, beverageOrders);

        return priceAmount - getCashDiscount(priceAmount);
    }

    public static double calculateCard(List<OrderMenu> cakeOrders, List<OrderMenu> beverageOrders) {
        return calculatePrice(cakeOrders, beverageOrders);
    }

    private static int calculatePrice(List<OrderMenu> cakeOrders, List<OrderMenu> beverageOrders) {
        int cakePrice = cakeOrders.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum() - getCakeDiscount(cakeOrders);

        int beveragePrice = beverageOrders.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();

        return cakePrice + beveragePrice;
    }

    private static int getCakeDiscount(List<OrderMenu> cakeOrders) {
        return (cakeOrders.size() / CAKE_ORDER_COUNT) * CAKE_DISCOUNT;
    }

    private static double getCashDiscount(int cashAmount) {
        return cashAmount * (CASH_DISCOUNT_RATE);
    }
}
