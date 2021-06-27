import domain.Menu;
import jdk.internal.util.xml.impl.Input;
import repository.MenuRepository;
import domain.Table;
import repository.OrderRepository;
import repository.TableRepository;
import service.CafeOrderService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        OutputView.printMain();
        final CafeOrderService cafeOrderService = new CafeOrderService(new OrderRepository());
        final List<Table> tables = TableRepository.tables();
        final int func = InputView.inputFunction();

        if(func == 1)
        {
            orderMenu(tables, cafeOrderService);
        }
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private static void orderMenu(List<Table> tables, CafeOrderService cafeOrderService)
    {
        OutputView.printTables(tables);
        int tableNum = InputView.inputTableNumber();
    }
}
