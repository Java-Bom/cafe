package domain;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEqualNumber(int tableNumber){ return this.number==number;}

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
