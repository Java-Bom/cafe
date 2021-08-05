package repository;

import domain.Menu;
import domain.Order;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

//주문 정보들을 저장하는 클래스
public class OrderRepository {
    private static final int MAX_NUMBER_PER_MENU = 30;
    private final List<Order> orders = new ArrayList<>();

    //주문을 받는다.
    public void addOrder(final Order order)
    {
        orders.add(order);
    }

    //해당 테이블에서 주문한 해당 메뉴의 수량을 return한다.
    public int countMenusNumber(int tableNumber, int menuNumber){
        int count= (int)orders.stream()
                .filter(order->order.isEqualTable(tableNumber))
                .filter(order->order.isEqualMenuNumber(menuNumber))
                .count();
        return count;
    }

    //해당 테이블에서 주문할 수 있는 한 메뉴의 최대 수량을 넘는지 아닌지 검사한다.
    public boolean checkMaximumPerMenu(int tableNumber, int menuNumber, int menuCount){
        int existedMenusCount = countMenusNumber(tableNumber, menuNumber);
        if(existedMenusCount+menuCount<=MAX_NUMBER_PER_MENU){
            return true;
        }
        return false;
    }
    //해당 테이블에서 주문한 것들을 모두 return한다.
    public List<Order> findByTableNumber(int tableNumber)
    {
        return 
                orders.stream()
                        .filter(order-> order.isEqualTable(tableNumber))
                        .collect(toList());
    }

    //해당 테이블에서 주문한 메뉴와 수량을 return한다.
    public Map<Menu, Long> getBill(int tableNumber){
        List<Order> orders = findByTableNumber(tableNumber);
        Map<Menu, Long> bill = orders.stream()
                .map(order -> MenuRepository.findByNumber(order.getMenuNumber()))
                .collect(Collectors.groupingBy(order->order,HashMap::new, counting()));

        return bill;
    }

    //지불이 완료되면 해당 테이블의 주문을 모두 삭제한다.
    public void finishedPayment(int tableNumber){
        orders.removeIf(order->order.isEqualTable(tableNumber));
    }

}