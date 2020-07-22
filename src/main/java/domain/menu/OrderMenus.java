package domain.menu;

import domain.vo.Amount;
import domain.vo.Quantity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class OrderMenus {

    private final List<OrderMenu> orderMenus;

    public OrderMenus() {
        orderMenus = new ArrayList<>();
    }

    public boolean containsOrderMenu(final OrderMenu newOrderMenu) {
        return orderMenus.stream()
                .anyMatch(orderMenu -> orderMenu.isSameMenu(newOrderMenu));
    }

    public void addMenu(final OrderMenu orderMenu) {
        if (containsOrderMenu(orderMenu)) {
            OrderMenu previousOrderMenu = getOrder(orderMenu);
            previousOrderMenu.extraOrder(orderMenu);
            return;
        }

        orderMenus.add(orderMenu);
    }

    private OrderMenu getOrder(final OrderMenu newOrder) {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameMenu(newOrder))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 주문입니다."));
    }

    private Quantity getQuantityByCategory(final Category discountCategory) {
        List<Quantity> quantities = orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameCategory(discountCategory))
                .map(OrderMenu::getQuantity)
                .collect(Collectors.toList());
        return Quantity.sum(quantities);
    }

    public Amount getTotalAmount() {
        Amount amount = Amount.valueOf(0);

        for (OrderMenu orderMenu : orderMenus) {
            amount = amount.add(orderMenu.getTotalAmount());
        }

        return amount;
    }

    public Amount getDiscountAmount(final Quantity discountQuantity,
                                    final Amount discountAmount,
                                    final Category discountCategory) {
        Amount totalAmount = getTotalAmount();
        Quantity quantity = getQuantityByCategory(discountCategory);
        Quantity TotalDiscountQuantity = quantity.divideQuantity(discountQuantity);
        Amount totalDiscountAmount = discountAmount.multiplyValue(TotalDiscountQuantity.getValue());
        return totalAmount.subtractionAmount(totalDiscountAmount);
    }

    public Amount getDiscountAmount(final double discountRate) {
        Amount totalAmount = getTotalAmount();
        return totalAmount.multiplyValue(discountRate);
    }
}
