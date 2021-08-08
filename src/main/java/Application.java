import domain.Menu;
//import jdk.internal.util.xml.impl.Input;
import domain.PayType;
import repository.MenuRepository;
import domain.Table;
import repository.OrderRepository;
import repository.TableRepository;
import service.CafeOrderService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        OutputView.printMain();
        final CafeOrderService cafeOrderService = new CafeOrderService(new OrderRepository());
        final List<Table> tables = TableRepository.tables();
        int func = InputView.inputFunction();

        while(func!=3)
        {
            if(func == 1)
            {
                orderMenu(tables, cafeOrderService);
            }
            if(func == 2)
            {
                payOrders(tables, cafeOrderService);
            }
            else if(func>3 || func<0){
                throw new NoSuchElementException("해당 기능은 존재하지 않습니다.");
            }
            OutputView.printMain();
            func = InputView.inputFunction();
        }
    }

    private static void payOrders(List<Table> tables, CafeOrderService cafeOrderService) {
        OutputView.printTables(tables, cafeOrderService);
        int tableNum = InputView.inputPayTableNumber();
        if(cafeOrderService.checkOrderedTable(tableNum)==false){
            OutputView.printNoOrder();
            return;
        };
        Map<Menu, Long> bill = cafeOrderService.getBillByTable(tableNum);
        OutputView.printBill(bill);
        OutputView.printPayMessage(tableNum);
        int payTypeNumber = InputView.inputPayType();
        PayType payType = PayType.findByNumber(payTypeNumber);
        long amountOfPayment = cafeOrderService.getAmountOfPayment(tableNum, payType);
        OutputView.printAmountOfPayment(amountOfPayment);
    }

    private static void orderMenu(List<Table> tables, CafeOrderService cafeOrderService)
    {
        OutputView.printTables(tables, cafeOrderService);
        int tableNum = InputView.inputTableNumber();
        boolean isValidTblNum = cafeOrderService.isValidTableNumber(tableNum);
        if(isValidTblNum==false){
            orderMenu(tables, cafeOrderService);
        }
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
        int menuNum = InputView.inputMenu();
        boolean isValidMenuNum = cafeOrderService.isValidMenuNumber(menuNum);
        if(isValidMenuNum==false){
            OutputView.printMenuAlert();
            orderMenu(tables, cafeOrderService);
        }
        int menuCount = InputView.inputCount();
        boolean isValidMenuCount = cafeOrderService.checkMaximumCount(tableNum, menuNum, menuCount);
        if(isValidMenuCount==false){
            OutputView.printMaxAlert();
            return;
        }
        cafeOrderService.orderMenu(menuNum, menuCount, tableNum);
    }
}
