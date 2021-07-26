package service;

import domain.*;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.Map;

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

    public Map<Menu,Long> getBillByTable(int tableNum) {
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

    public long getAmountOfPayment(int tableNum, PayType payType, Map<Menu, Long> bill) {
        int numberOfCakes = getNumOfCakes(bill);
        long amountOfPayment = getFinalPayment(bill, numberOfCakes, payType.getDiscountRate());
        orderRepository.finishedPayment(tableNum);

        return amountOfPayment;
    }

    private int getNumOfCakes(Map<Menu, Long> bill) {
        int numOfCakes = 0;
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            if(entry.getKey().getCategory()==Category.CAKE){
                numOfCakes += entry.getValue();
            }
        }
        return numOfCakes;
    }

    public long getSumOfPayment(Map<Menu, Long> bill){
        long totalSumOfPayment = 0;
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            totalSumOfPayment += entry.getKey().getPrice()*entry.getValue();
        }
        return totalSumOfPayment;
    }

    private long getFinalPayment(Map<Menu,Long> bill, int numberOfCakes, int discountRate){
        long totalSumOfPayment = getSumOfPayment(bill);
        if(numberOfCakes>=DISCOUNT_FOR_CAKES){
            totalSumOfPayment -= DISCOUNT_MONEY_PER_NUM_OF_CAKES*(numberOfCakes/3);
        }
        totalSumOfPayment = (long)(totalSumOfPayment - totalSumOfPayment*((float)discountRate/100));
        return totalSumOfPayment;
    }
}
