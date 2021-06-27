package service;

import domain.Order;
import repository.OrderRepository;

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

}
