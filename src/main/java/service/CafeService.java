package service;

import domain.Menu;
import domain.MenuRepository;
import domain.Order;
import domain.OrderRepository;

public class CafeService {
    private final OrderRepository orderRepository;

    public CafeService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderMenu(final int menumNumber, final int tableNumber) {
        orderRepository.addOrder(new Order(menumNumber, tableNumber));
    }

    public long calculate(final int tableNumber) {
        return orderRepository.findByTable(tableNumber).stream()
                .map(Order::getMenuNumber)
                .map(MenuRepository::findByNumber)
                .map(Menu::getPrice)
                .mapToInt(Integer::intValue).sum();
    }
}
