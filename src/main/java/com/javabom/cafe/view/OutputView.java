package com.javabom.cafe.view;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";

    public static void printTables(final List<CafeTable> cafeTables) {
        System.out.println();
        System.out.println("## 테이블 목록");
        final int size = cafeTables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(cafeTables);
        printLine(BOTTOM_LINE, size);
    }

    public static void printPaymentAmount(final Amount amount) {
        System.out.println();
        System.out.println("## 최종 결제 금액");
        System.out.println(amount.toString());
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

    private static void printTableNumbers(final List<CafeTable> cafeTables) {
        for (final CafeTable cafeTable : cafeTables) {
            System.out.printf(TABLE_FORMAT, cafeTable);
        }
        System.out.println();
    }

}
