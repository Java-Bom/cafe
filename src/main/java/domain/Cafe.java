package domain;

import java.util.Collections;
import java.util.List;

public class Cafe {

    private final List<Table> tables;
    private final List<Menu> menus;
    private boolean isOpen = true;

    public Cafe(List<Table> tables, List<Menu> menus) {
        this.tables = tables;
        this.menus = menus;
    }

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

    public void addTable(final Table table) {
        tables.add(table);
    }

    public void deleteTable(final int tableNumber) {
        tables.removeIf(table -> table.getNumber() == tableNumber);
    }

    public void addMenu(final Menu menu) {
        menus.add(menu);
    }

    public void deleteMenu(final int menuNumber) {
        menus.removeIf(menu -> menu.getNumber() == menuNumber);
    }

    public void orderMenu(final int tableNumber, final int menuNumber, final int menuQuantity) {
        Table table = findTableByNumber(tableNumber);
        Menu menu = findMenuByNumber(menuNumber);
        table.addMenu(menu, menuQuantity);
    }

    private Table findTableByNumber(final int tableNumber) {
        return this.tables.stream()
                .filter(table -> table.getNumber() == tableNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d번 테이블은 존재하지 않습니다.", tableNumber)));
    }

    private Menu findMenuByNumber(int menuNumber) {
        return this.menus.stream()
                .filter(menu -> menu.getNumber() == menuNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d번 메뉴는 존재하지 않습니다.", menuNumber)));
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
