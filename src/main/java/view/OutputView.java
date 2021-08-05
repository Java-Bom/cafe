package view;

import domain.Menu;
import domain.Table;
import repository.OrderRepository;
import service.CafeOrderService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String BOTTOM_LINE_ORDERED_TBL= "└ \\$ ┘";

    public static void printMain()
    {
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 종료");
    }

    public static void printTables(final List<Table> tables, final CafeOrderService cafeOrderService) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printLine(BOTTOM_LINE, tables, cafeOrderService);
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

    private static void printLine(final String line, final List<Table> tables, CafeOrderService cafeOrderService){
        for(final Table table : tables) {
            if (cafeOrderService.isOrderedTable(table.getNumber())) {
                System.out.print(BOTTOM_LINE_ORDERED_TBL);
            } else {
                System.out.print(BOTTOM_LINE);
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

    public static void printOrders(Map<Menu, Long> bill) {
        System.out.println("\n## 주문 내역");
        System.out.println("메뉴 수량 금액");
        bill.forEach((key, value)->{
            System.out.println(key.getName()+" "+value+" "+key.getPrice()*value);
        });
    }

    public static void printMaxAlert()
    {
        System.out.println("\n## 한 테이블에 주문 할 수 있는 한 메뉴의 수량은 최대 30개입니다.");
    }
    public static void printMenuAlert()
    {
        System.out.println("\n## 해당 메뉴는 존재하지 않는 메뉴입니다");
    }

    public static void printPayMessage(int tableNum) {
        System.out.println("\n## "+tableNum+"번 테이블의 결제를 진행합니다.");
    }

    public static void printAmountOfPayment(long amountOfPayment) {
        System.out.println("## 최종 결제 금액");
        System.out.println(amountOfPayment);
    }

    public static void printNoOrder(){
        System.out.println("주문 정보가 없는 테이블입니다.\n");
    }

    public static void printNoTableAlert() {
        System.out.println("## 존재하지 않는 테이블입니다. \n테이블을 다시 선택하세요\n");
    }
}
