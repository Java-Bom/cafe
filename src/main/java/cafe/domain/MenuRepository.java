package cafe.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuRepository {
    private static final List<cafe.domain.Menu> menus = new ArrayList<>();

    static {
        menus.add(new cafe.domain.Menu(1, "가나슈", cafe.domain.Category.CAKE, 7000));
        menus.add(new cafe.domain.Menu(2, "당근 케이크", cafe.domain.Category.CAKE, 6500));
        menus.add(new cafe.domain.Menu(3, "아이스 박스", cafe.domain.Category.CAKE, 8000));
        menus.add(new cafe.domain.Menu(4, "티라미수", cafe.domain.Category.CAKE, 5500));
        menus.add(new cafe.domain.Menu(5, "치즈 케이크", cafe.domain.Category.CAKE, 7000));
        menus.add(new cafe.domain.Menu(6, "얼그레이 케이크", cafe.domain.Category.CAKE, 7000));
        menus.add(new cafe.domain.Menu(21, "아메리카노", cafe.domain.Category.BEVERAGE, 4500));
        menus.add(new cafe.domain.Menu(22, "에스프레소", cafe.domain.Category.BEVERAGE, 4000));
        menus.add(new cafe.domain.Menu(23, "카푸치노", cafe.domain.Category.BEVERAGE, 5000));
        menus.add(new cafe.domain.Menu(24, "그린티 라떼", cafe.domain.Category.BEVERAGE, 6000));
    }

    public static List<cafe.domain.Menu> menus() {
        return Collections.unmodifiableList(menus);
    }

    public static cafe.domain.Menu findByNumber(final int number) {
        return menus().stream()
                .filter(menu -> menu.isSameMenu(number))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
