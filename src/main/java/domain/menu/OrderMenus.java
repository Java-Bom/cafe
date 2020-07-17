package domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderMenus {

    private final List<OrderMenu> orderMenus;

    public OrderMenus() {
        orderMenus = new ArrayList<>();
    }

    public boolean containsOrderMenu(final OrderMenu newOrderMenu) {
        long count = orderMenus.stream()
                .filter(orderMenu -> orderMenu.isSameMenu(newOrderMenu))
                .count();
        return count > 0;
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
                .get();
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }
}
