package service;

import domain.Menu;
import domain.Order;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CafeOrderService {
    private static final int DISCOUNT_FOR_CAKES = 3;
    private static final int DISCOUNT_MONEY_PER_NUM_OF_CAKES = 3000;

    private final OrderRepository orderRepository;

    public CafeOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean isValidTableNum(int tableNum)
    {
        if(tableNum<0||tableNum==7||tableNum>9){
            System.out.println("## 존재하지 않는 테이블입니다. \n테이블을 다시 선택하세요\n");
            return false;
        }
        else{
            return true;
        }
    }

    public boolean canOrderMenu(int tableNum, int menuNum, int menuCount)
    {
        return orderRepository.canOrder(tableNum, menuNum, menuCount);
    }

    public void orderMenu(int menuNum, int count, int tableNum)
    {
        for(int i=0;i<count;i++){
            orderRepository.addOrder(new Order(menuNum, tableNum));
        }
    }

    public Map<Menu,Long> getOrdersByTable(int tableNum) {
        return orderRepository.getBill(tableNum);
    }

    public boolean isOrderedTable(int tableNum){
        if(orderRepository.getBill(tableNum).isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkMenuNum(int menuNum) {
        try{
            return MenuRepository.findByNumber(menuNum) instanceof Menu;
        }catch (Exception e){
            return false;
        }
    }

}
