package com.javabom.cafe.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputFunction() {
        System.out.println();
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 종료");

        System.out.println();
        System.out.println("## 기능 선택");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputTableNumber() {
        System.out.println();
        System.out.println("## 주문할 테이블을 선택하세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputPaymentTableNumber() {
        System.out.println();
        System.out.println("## 테이블 선택");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputMenuNumber() {
        System.out.println();
        System.out.println("## 메뉴 선택");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputQuantity() {
        System.out.println();
        System.out.println("## 수량 선택");

        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputPaymentMethod(final int tableNum) {
        System.out.println();
        System.out.printf("## %d 테이블 결제 진행합니다.\n", tableNum);
        System.out.println("## 신용 카드는 1번, 현금 결제는 2번 ");

        return Integer.parseInt(scanner.nextLine());
    }
}
