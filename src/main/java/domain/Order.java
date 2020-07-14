package domain;

import java.util.Objects;

public class Order {
    private final int menuNumber;
    private final int tableNumber;

    private Order() {
        throw new AssertionError();
    }

    public Order(final int menuNumber, final int tableNumber) {
        this.menuNumber = menuNumber;
        this.tableNumber = tableNumber;
    }

    public boolean isEqualTableNumberTo(final Table table) {
        return this.tableNumber == table.getNumber();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return menuNumber == order.menuNumber &&
                tableNumber == order.tableNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuNumber, tableNumber);
    }
}
