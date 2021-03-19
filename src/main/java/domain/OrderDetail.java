package domain;

public class OrderDetail {
    private final Menu menu;
    private final int quantity;

    public OrderDetail(final Menu menu, final int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", menu.getName(), quantity, menu.getPrice() * quantity);
    }

}
