package view;

import domain.POS;
import domain.menu.Menu;
import domain.menu.Menus;
import domain.table.Table;
import domain.vo.Money;
import domain.vo.Quantity;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String NONE_ORDER_BOTTOM_LINE = "└ ─ ┘";
    private static final String ORDER_BOTTOM_LINE = "└ $ ┘";

    public static void printTables(final POS POS) {
        System.out.println("## 테이블 목록");
        Map<Table, domain.menu.Menus> tables = POS.get();
        printLine(TOP_LINE, tables.size());
        printTableNumbers(tables);
        printTableBottomLine(tables);
    }

    public static void printMainView() {
        System.out.println("## 메인 화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 종료");
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final Map<Table, Menus> tables) {
        for (final Table table : tables.keySet()) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    private static void printTableBottomLine(final Map<Table, Menus> tables) {
        for (Table table : tables.keySet()) {
            domain.menu.Menus menus = tables.get(table);
            if (menus.isEmpty()) {
                System.out.print(NONE_ORDER_BOTTOM_LINE);
            } else {
                System.out.print(ORDER_BOTTOM_LINE);
            }
        }
        System.out.println();
    }

    public static void printOrderHistory(final Menus menus) {
        Map<Menu, Quantity> orderHistory = menus.get();

        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        for (Menu menu : orderHistory.keySet()) {
            System.out.println(menu.getName() + " " + menu.getPrice() + " " + orderHistory.get(menu).get());
        }
    }

    public static void printPaymentOf(final int tableNumber) {
        System.out.println(String.format("%d번 테이블의 결제를 진행합니다", tableNumber));
    }

    public static void printFinalAmount(final Money amount) {
        System.out.println("## 최종 결제 금액");
        System.out.println(amount.get());
    }
}
