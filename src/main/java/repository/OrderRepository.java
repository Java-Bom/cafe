package repository;

import domain.Menu;
import domain.Order;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class OrderRepository {
    private static final int MAX_NUMBER_PER_MENU = 30;
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(final Order order)
    {
        orders.add(order);
    }

    public int countMenusNumber(int tableNum, int menuNum){
        int count= (int)orders.stream()
                .filter(order->order.isEqualTable(tableNum))
                .filter(order->menuNum==order.getMenuNum())
                .count();
        return count;
    }

    public boolean canOrder(int tableNum, int menuNum, int menuCount){
        int existedMenusNum = countMenusNumber(tableNum, menuNum);
        if(existedMenusNum+menuCount<=MAX_NUMBER_PER_MENU){
            return true;
        }else{
            return false;
        }
    }

    public List<Order> findByTableNumber(int tableNum)
    {
        return 
                orders.stream()
                        .filter(order-> order.isEqualTable(tableNum))
                        .collect(toList());
    }

    //해당 테이블에서 해당 메뉴의 수량을 출력하는 메소드
    public Map<Menu, Long> getBill(int tableNum){
        List<Order> orders = findByTableNumber(tableNum);
        Map<Menu, Long> bill = orders.stream()
                .map(order -> MenuRepository.findByNumber(order.getMenuNum()))
                .collect(Collectors.groupingBy(order->order,HashMap::new, counting()));

        return bill;
    }

    public void finishedPayment(int tableNum){
        orders.removeIf(order->order.isEqualTable(tableNum));
    }

}