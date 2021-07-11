package repository;

import domain.Menu;
import domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderRepository {
    private static final int MAX_MENU_PER_TABLE = 30;
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order)
    {
        orders.add(order);
    }

    public int countOrders(){
        return orders.size();
    }

    public Stream<Order> findByTableNumber(int tableNum)
    {
        //table Number를 입력받아서, 해당 테이블에서 주문한 메뉴+수량+금 모두 출력
        return 
                orders.stream()
                        .filter(order->(Integer.toString(order.getTableNum()).equals(Integer.toString(tableNum))));
        
    }

}