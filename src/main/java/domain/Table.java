package domain;

import java.util.List;

public class Table {
    private final int number;
    private OrderMenus orderMenus = OrderMenus.empty();

    public Table(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addMenu(Menu menu, int quantity) {
        this.orderMenus.addMenu(menu, quantity);
    }

    public boolean hasMenu() {
        return orderMenus.isNotEmpty();
    }

    public List<OrderDetail> getOrderDetails() {
        return orderMenus.getOrderDetails();
    }

    public void clear(){
        this.orderMenus = OrderMenus.empty();
    }

    public int getTotalPrice(Payment payment){
        return this.orderMenus.getTotalPrice(payment);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
