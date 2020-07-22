package domain.table;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public boolean isSameNumber(final int tableNumber) {
        return number == tableNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

}
