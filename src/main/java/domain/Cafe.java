package domain;

import java.util.Collections;
import java.util.List;

import static domain.MenuRepository.findMenuByNumber;
import static domain.TableRepository.findTableByNumber;

public class Cafe {

    private final List<Table> tables = TableRepository.tables();
    private final List<Menu> menus = MenuRepository.menus();
    private boolean isOpen = true;

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void close() {
        this.isOpen = false;
    }

    public void orderMenu(final int tableNumber, final int menuNumber, final int menuQuantity) {
        Table table = findTableByNumber(tableNumber);
        Menu menu = findMenuByNumber(menuNumber);
        table.addMenu(menu, menuQuantity);
    }

    public List<OrderDetail> getOrderMenusTableOf(final int tableNumber) {
        Table table = findTableByNumber(tableNumber);
        return table.getOrderDetails();
    }

    public int payOrders(final int tableNumber, final Payment payment) {
        Table table = findTableByNumber(tableNumber);
        validateTableHasMenu(table);
        int totalPrice = table.getTotalPrice(payment);
        table.clear();
        return totalPrice;
    }

    private void validateTableHasMenu(Table table) {
        if (!table.hasMenu()) {
            throw new IllegalArgumentException("해당 테이블에는 주문한 메뉴가 없습니다.");
        }
    }
}
