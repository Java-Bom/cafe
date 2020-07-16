package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenus {
    private static final int DISCOUNT_CAKE_COUNT = 3;
    private static final int DISCOUNT_CAKE_PRICE = 3000;
    private static final int MAX_ONE_MENU_QUANTITY = 30;

    private final List<Menu> menus = new ArrayList<>();

    private OrderMenus() {
    }

    public static OrderMenus empty() {
        return new OrderMenus();
    }

    public void addMenu(final Menu menu, final int quantity) {
        validateSameMenuMaxCount(menu, quantity);
        for (int i = 0; i < quantity; i++) {
            this.menus.add(menu);
        }
    }

    private void validateSameMenuMaxCount(final Menu menu, final int quantity) {
        int currentMenuQuantity = getMenuQuantityOf(menu);
        if (currentMenuQuantity + quantity > MAX_ONE_MENU_QUANTITY) {
            throw new IllegalArgumentException(
                    String.format("한 메뉴의 수량은 30개를 초과할 수 없습니다. 기존 메뉴수량 : %d, 새로 들어온 수량 : %d", currentMenuQuantity, quantity));
        }
    }

    private int getMenuQuantityOf(final Menu specificMenu) {
        return (int) this.menus.stream()
                .filter(menu -> menu == specificMenu)
                .count();
    }

    private List<Menu> getMenuKinds() {
        return this.menus.stream()
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isNotEmpty() {
        return !this.menus.isEmpty();
    }

    public List<OrderDetail> getOrderDetails() {
        return getMenuKinds().stream()
                .map(menu -> new OrderDetail(menu, getMenuQuantityOf(menu)))
                .collect(Collectors.toUnmodifiableList());
    }

    public int getTotalPrice(Payment payment) {
        int cakeDiscountCount = getCategoryCountOf(Category.CAKE) / DISCOUNT_CAKE_COUNT;
        int firstDiscountedPrice = getOriginalTotalPrice() - (cakeDiscountCount * DISCOUNT_CAKE_PRICE);
        return payment.calculateFinalPrice(firstDiscountedPrice);
    }

    private int getOriginalTotalPrice() {
        return this.menus.stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }

    private int getCategoryCountOf(Category category) {
        return (int) this.menus.stream()
                .filter(menu -> menu.getCategory() == category)
                .count();
    }
}
