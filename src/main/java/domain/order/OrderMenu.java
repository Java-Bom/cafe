package domain.order;

import domain.menu.Menu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderMenu {
    private final Menu menu;
    private final Quantity quantity;


}
