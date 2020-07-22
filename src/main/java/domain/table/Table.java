package domain.table;

import java.util.Objects;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public boolean equalsOf(final int number) {
        return this.number == number;
    }

    public boolean equalsOf(final Table table) {
        return this.number == table.number;
    }

    public int get() {
        return this.number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        final Table table = (Table) o;
        return number == table.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
