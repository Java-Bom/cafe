package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputFunction() {
        System.out.println("## 기능 선택");
        return scanner.nextInt();
    }

    public static int inputMenuIdx() {
        System.out.println("## 메뉴 선택");
        return scanner.nextInt();

    }

    public static int inputQuantity() {
        System.out.println("## 수량 선택");
        return scanner.nextInt();

    }

    public static int inputPaymentMethod() {
        System.out.println("## 신용 카드는 1번, 현금 결제는 2번");
        return scanner.nextInt();

    }
}
