package service;

import domain.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static domain.Category.CAKE;
import static java.util.stream.Collectors.*;

public class CafeService {
    private final OrderRepository orderRepository;

    public CafeService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderMenu(final int menumNumber, final int count, final int tableNumber) {
        for (int i = 0; i < count; i++) {
            orderRepository.addOrder(new Order(menumNumber, tableNumber));
        }
    }

    public Map<Menu, Long> findBillsByTable(final int tableNumber) {
        List<Order> orders = orderRepository.findByTable(tableNumber);
        return orders.stream()
                .map(order -> MenuRepository.findByNumber(order.getMenuNumber()))
                .collect(groupingBy(Function.identity(), counting()));
    }

    public long calculate(final int tableNumber, PayType payType) {
        List<Menu> menus = getMenusByTable(tableNumber);
        long numberOfCake = menus.stream()
                .filter(menu -> menu.isCategory(CAKE))
                .count();
        float discountRate = payType.getDiscountRate();

        orderRepository.deleteByTable(tableNumber);

        return (long) ((menus.stream()
                .mapToInt(Menu::getPrice)
                .sum() - ((numberOfCake / 3) * 3000)) * discountRate);
    }

    private List<Menu> getMenusByTable(final int tableNumber) {
        return orderRepository.findByTable(tableNumber).stream()
                .map(Order::getMenuNumber)
                .map(MenuRepository::findByNumber)
                .collect(toList());
    }

    public boolean hasBills(final int tableNumber) {
        return !orderRepository.findByTable(tableNumber).isEmpty();
    }
}
