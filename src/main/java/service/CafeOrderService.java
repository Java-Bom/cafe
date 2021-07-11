package service;

import domain.Menu;
import domain.Order;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CafeOrderService {
    private static final int DISCOUNT_FOR_CAKES = 3;
    private static final int DISCOUNT_MONEY_PER_NUM_OF_CAKES = 3000;

    private final OrderRepository orderRepository;

    public CafeOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderMenu(int menuNum, int count, int tableNum)
    {
        for(int i=0;i<count;i++){
            orderRepository.addOrder(new Order(menuNum, tableNum));
        }
    }

    public void getOrdersByTable(int tableNum) {
        List<Integer> orderedMenusNum = orderRepository
                .findByTableNumber(tableNum)
                .map(Order::getMenuNum)
                .collect(Collectors.toList());

        List<Optional<Menu>> menus = new ArrayList<>();

        for (int i = 0; i < orderRepository.countOrders(); i++) {
            menus.add(MenuRepository.findByNumber(orderedMenusNum.get(i).intValue()-1));
        }

        for (int i = 0; i < menus.size(); i++){
            menus.get(i).ifPresent(menu ->
                    System.out.println(menu.getName()+" "+menu.getNumber()+" "+menu.getPrice()));
        }

        Optional.ofNullable(menus).orElseGet(() -> {
            System.out.println(menus.iterator().next().get().getName());
            return null;
        });
    }

}
