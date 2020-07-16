package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMain() {
        System.out.println("## 메인화면\n1 - 주문등록\n2 - 결제하기\n3 - 종료");
        return scanner.nextInt();
    }

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputTableNumberForCalculate() {
        System.out.println("## 계산할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputMenuNumber() {
        System.out.println("## 메뉴선택");
        return scanner.nextInt();
    }

    public static int inputMenuCount() {
        System.out.println("## 총 수량을 입력하세요");
        return scanner.nextInt();
    }

    public static int askPayType(final int tableNumber) {
        System.out.printf("## %d번 테이블의 결제를 진행합니다.%n", tableNumber);
        System.out.printf("## 신용 카드는 1번, 현금 결제는 2번 %n");
        return scanner.nextInt();
    }

}
