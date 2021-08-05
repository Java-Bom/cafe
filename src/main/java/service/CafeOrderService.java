package service;

import domain.*;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.TableRepository;
import view.OutputView;

import java.util.Map;

public class CafeOrderService {
    private static final int DISCOUNT_FOR_CAKES = 3;
    private static final int DISCOUNT_MONEY_PER_NUM_OF_CAKES = 3000;

    private final OrderRepository orderRepository;

    public CafeOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //해당 테이블이 존재하는 테이블인지 검사한다.
    public boolean isValidTableNumber(int tableNumber)
    {
        if(TableRepository.findByNumber(tableNumber).isPresent()){
            return true;
        }
        return false;
    }

    //주문하려는 메뉴의 수량이 누적 30개가 넘는지 확인한다.
    public boolean checkMaximumCount(int tableNumber, int menuNumber, int menuCount)
    {
        return orderRepository.checkMaximumPerMenu(tableNumber, menuNumber, menuCount);
    }

    //메뉴의 번호, 수량, 테이블 번호를 입력받고 수량만큼의 주문을 추가한다.
    public void orderMenu(int menuNumber, int count, int tableNumber)
    {
        for(int i=0;i<count;i++){
            orderRepository.addOrder(new Order(menuNumber, tableNumber));
        }
    }

    //해당 테이블에서 주문한 것들에 대한 영수증을 return한다.
    public Map<Menu,Long> getBillByTable(int tableNumber) {
        return orderRepository.getBill(tableNumber);
    }

    //해당 테이블에 주문이 들어간 테이블인지 검사한다.
    public boolean isOrderedTable(int tableNumber){
        if(this.getBillByTable(tableNumber).isEmpty()){
            return false;
        }
        return true;
    }

    //입력된 메뉴가 존재하는 메뉴인지 검사한다.
    public boolean isValidMenuNumber(int menuNumber) {
        if(MenuRepository.checkMenuNumber(menuNumber)){
            return true;
        }
        return false;
    }

    //지불해야하는 총 금액을 return한다.
    public long getAmountOfPayment(int tableNumber, PayType payType, Map<Menu, Long> bill) {
        int numberOfCakes = getNumberOfCakes(bill);
        long amountOfPayment = getFinalPayment(bill, numberOfCakes, payType.getDiscountRate());
        orderRepository.finishedPayment(tableNumber);

        return amountOfPayment;
    }

    //해당 테이블에서 주문한 케익의 갯수를 return한다.
    private int getNumberOfCakes(Map<Menu, Long> bill) {
        int numberOfCakes = 0;
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            numberOfCakes += entry.getKey().isThisCake() ? entry.getValue() : 0;
        }
        return numberOfCakes;
    }

    //해당 테이블에서 주문한 메뉴들 가격의 총 합을 계산한다.
    private long getSumOfPayment(Map<Menu, Long> bill){
        long totalSumOfPayment = 0;
        for (Map.Entry<Menu, Long> entry : bill.entrySet()){
            totalSumOfPayment += entry.getKey().getPrice() * entry.getValue();
        }
        return totalSumOfPayment;
    }

    //할인을 적용한 가격을 계산한다.
    private long getFinalPayment(Map<Menu,Long> bill, int numberOfCakes, int discountRate){
        long totalSumOfPayment = getSumOfPayment(bill);
        if(numberOfCakes>=DISCOUNT_FOR_CAKES){
            totalSumOfPayment -= DISCOUNT_MONEY_PER_NUM_OF_CAKES * (numberOfCakes/3);
        }
        totalSumOfPayment = (long)(totalSumOfPayment - totalSumOfPayment*((float)discountRate/100));
        return totalSumOfPayment;
    }
}
