package cafe.service;

import cafe.domain.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static cafe.domain.Category.CAKE;
import static java.util.stream.Collectors.*;

public class CafeService {
    private static final int DISCOUNT_CAKE_BASE_COUNT = 3;
    private static final int DISCOUNT_MONEY_PER_BASE_COUNT = 3000;

    private final OrderRepository orderRepository;

    public CafeService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderMenu(final int menuNumber, final int count, final int tableNumber) {
        for (int i = 0; i < count; i++) {
            orderRepository.addOrder(new Order(menuNumber, tableNumber));
        }
    }

    public Bill findBillsByTable(final int tableNumber) {
        List<Order> orders = orderRepository.findByTableNumber(tableNumber);
        Map<Menu, Long> bill = orders.stream()
                .map(order -> MenuRepository.findByNumber(order.getMenuNumber()))
                .collect(groupingBy(Function.identity(), counting()));
        return new Bill(bill);
    }

    public long calculate(final int tableNumber, PayType payType) {
        List<Menu> menus = getMenusByTable(tableNumber);
        long numberOfCake = menus.stream()
                .filter(menu -> menu.isCategory(CAKE))
                .count();
        int discountMoney = (int) (numberOfCake / DISCOUNT_CAKE_BASE_COUNT) * DISCOUNT_MONEY_PER_BASE_COUNT;
        float discountRate = payType.getDiscountRate();

        orderRepository.resolveByTableNumber(tableNumber);

        return (long) ((menus.stream()
                .mapToInt(Menu::getPrice)
                .sum() - discountMoney) * discountRate);
    }

    private List<Menu> getMenusByTable(final int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber).stream()
                .map(Order::getMenuNumber)
                .map(MenuRepository::findByNumber)
                .collect(toList());
    }

    public boolean hasBills(final int tableNumber) {
        return !orderRepository.findByTableNumber(tableNumber).isEmpty();
    }
}
