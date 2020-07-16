package domain.menu;

import domain.enums.Category;
import domain.vo.Money;
import domain.vo.Quantity;

import java.util.*;

public class Menus {
    private final Map<Menu, Quantity> menus;

    public Menus() {
        this.menus = new HashMap<>();
    }

    public void addMenu(final int menuIdx, final int quantity) {
        checkNegativeQuantity(quantity);
        Menu menu = MenuRepository.find(menuIdx);
        this.menus.put(menu, menus.getOrDefault(menu, Quantity.ZERO).plus(quantity));
    }

    private void checkNegativeQuantity(final int quantity) {
        if (quantity <= Quantity.MIN_QUANTITY) {
            throw new IllegalArgumentException(String.format("주문 수량: %d, 최소 1개 이상의 주문을 하셔야 합니다.", quantity));
        }
    }

    public Money getAmount() {
        int amount = 0;
        for (Menu menu : menus.keySet()) {
            amount += menu.getMultiplyPriceOf(menus.get(menu));
        }

        return Money.of(amount);
    }

    public Quantity getQuantityByCategory(Category category) {
        Quantity quantity = Quantity.ZERO;
        for (Menu menu : menus.keySet()) {
            quantity = quantity.plus(getQuantityByEquals(menu, category));
        }

        return quantity;
    }

    private Quantity getQuantityByEquals(Menu menu, Category category) {
        return menu.equalsOf(category) ? menus.get(menu) : Quantity.ZERO;
    }

    public boolean isEmpty() {
        return menus.keySet().stream()
                .allMatch(menu -> menus.get(menu).equals(Quantity.ZERO));
    }

    public Map<Menu, Quantity> get() {
        return Collections.unmodifiableMap(this.menus);
    }
}
