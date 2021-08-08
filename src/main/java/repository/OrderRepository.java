package repository;

import domain.Menu;
import domain.Order;
import domain.PayType;
import service.CafeOrderService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

//주문 정보들을 저장하고 주문한 것들의 합을 계산한다.
public class OrderRepository{
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

    //해당 테이블이 주문된 테이블인지 검사한다.
    public boolean isOrderedTable(int tableNumber){
        if(getBill(tableNumber).isEmpty()){
            return false;
        }
        return true;
    }

    //해당 테이블에서 주문한 케익의 갯수를 계산한다.
    public int getNumberOfCakes(int tableNumber) {
        int numberOfCakes = 0;
        Map<Menu, Long> bill = getBill(tableNumber);
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            numberOfCakes += entry.getKey().isThisCake() ? entry.getValue() : 0;
        }
        return numberOfCakes;
    }

    //해당 테이블에서 주문한 메뉴들 가격의 총 합을 계산한다.
    public long getSumOfPayment(int tableNumber){
        long totalSumOfPayment = 0;
        Map<Menu, Long> bill = getBill(tableNumber);
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            totalSumOfPayment += entry.getKey().getPrice() * entry.getValue();
        }
        return totalSumOfPayment;
    }

}