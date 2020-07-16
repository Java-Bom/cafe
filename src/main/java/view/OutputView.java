package view;

import domain.Menu;
import domain.Table;
import service.CafeService;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String BOTTOM_LINE_HAS_BILLS = "└ $ ┘";

    public static void printTables(final List<Table> tables, final CafeService cafeService) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printLine(BOTTOM_LINE, size, cafeService);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    public static void printOrders(CafeService cafeService, int tableNumber) {
        Map<Menu, Long> bills = cafeService.findBillsByTable(tableNumber);
        System.out.println("|   메뉴   |   수량   |   금액   |");
        for (Map.Entry<Menu, Long> bill : bills.entrySet()) {
            System.out.printf("%s  %d  %d %n", bill.getKey().getName(), bill.getValue(), bill.getKey().getPrice() * bill.getValue());
        }
    }

    private static void printLine(final String line, final int count) {
        for (int tableNo = 1; tableNo <= count; tableNo++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printLine(final String line, final int count, final CafeService cafeService) {
        for (int tableNo = 1; tableNo <= count; tableNo++) {
            if (cafeService.hasBills(tableNo)) {
                System.out.print(BOTTOM_LINE_HAS_BILLS);
            } else {
                System.out.print(line);
            }
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printTotalPrice(final long totalPrice) {
        System.out.println(totalPrice);
    }
}
