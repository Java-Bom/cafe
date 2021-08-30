package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.Category;
import domain.Menu;

public class MenuRepository {
	private static final List<Menu> menus = new ArrayList<>();

	static {
		menus.add(new Menu(1, "가나슈", Category.CAKE, 7000));
		menus.add(new Menu(2, "당근 케이크", Category.CAKE, 6500));
		menus.add(new Menu(3, "아이스 박스", Category.CAKE, 8000));
		menus.add(new Menu(4, "티라미수", Category.CAKE, 5500));
		menus.add(new Menu(5, "치즈 케이크", Category.CAKE, 7000));
		menus.add(new Menu(6, "얼그레이 케이크", Category.CAKE, 7000));
		menus.add(new Menu(21, "아메리카노", Category.BEVERAGE, 4500));
		menus.add(new Menu(22, "에스프레소", Category.BEVERAGE, 4000));
		menus.add(new Menu(23, "카푸치노", Category.BEVERAGE, 5000));
		menus.add(new Menu(24, "그린티 라떼", Category.BEVERAGE, 6000));
	}

	//해당 숫자에 해당하는 메뉴 객체를 return한다.
	public static Menu findByNumber(int number) {
		return menus.stream()
			.filter(menu -> menu.isEqualNumber(number))
			.findFirst()
			.orElseThrow(IllegalAccessError::new);
	}

	//입력된 숫자에 해당하는 메뉴가 있는지 검사한다.
	public static boolean checkMenuNumber(int number) {
		Menu menu = findByNumber(number);
		return menu.isEqualNumber(number);
	}

	public static List<Menu> menus() {
		return Collections.unmodifiableList(menus);
	}

	public Optional<Menu> findByName(String name) {
		return menus.stream()
			.filter(menu -> menu.getName().equals(name))
			.findAny();
	}
}
