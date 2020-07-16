import domain.MenuRepository;
import domain.OrderRepository;
import domain.Table;
import domain.TableRepository;
import service.CafeService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        final CafeService cafeService = new CafeService(new OrderRepository());

        int selectNo = InputView.showMain();

        while (selectNo != 3) {
            if (selectNo == 1) { // 주문
                OutputView.printTables(tables);
                int tableNumber = InputView.inputTableNumber();
                OutputView.printMenus(MenuRepository.menus());
                int menuNumber = InputView.inputMenuNumber();
                cafeService.orderMenu(menuNumber, tableNumber);
            }
            if (selectNo == 2) {
                OutputView.printTables(tables);
                int tableNumber = InputView.inputTableNumber();
                System.out.println("최종결제금액: " + cafeService.calculate(tableNumber));
            }
            selectNo = InputView.showMain();
        }
    }
}
