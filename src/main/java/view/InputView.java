package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputFunction(){
        System.out.println("## 기능 선택");
        return scanner.nextInt();
    }

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputPayTableNumber(){
        System.out.println("## 테이블 선택");
        return scanner.nextInt();
    }

    public static int inputMenu(){
        System.out.println("## 메뉴 선택");
        return scanner.nextInt();
    }

    public static int inputCount(){
        System.out.println("## 수량 선택");
        return scanner.nextInt();
    }
}
