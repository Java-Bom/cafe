package view;

import java.util.Scanner;

//사용자의 입력과 관련된 모든 일을 담당하는 클래스
public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputFunction() {
		System.out.println("\n## 기능 선택");
		return scanner.nextInt();
	}

	public static int inputTableNumber() {
		System.out.println("## 주문할 테이블을 선택하세요.");
		return scanner.nextInt();
	}

	public static int inputPayTableNumber() {
		System.out.println("\n## 테이블 선택");
		return scanner.nextInt();
	}

	public static int inputMenu() {
		System.out.println("\n## 메뉴 선택");
		return scanner.nextInt();
	}

	public static int inputCount() {
		System.out.println("\n## 수량 선택");
		return scanner.nextInt();
	}

	public static int inputPayType() {
		System.out.println("## 신용카드는 1번, 현금 결제는 2번");
		return scanner.nextInt();
	}
}
