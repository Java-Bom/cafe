package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static Table getTable(final int tableNumber) {
        validTableNumber(tableNumber);
        return tables.get(tableNumber-1);
    }

    private static void validTableNumber(final int tableNumber) {
        long count = tables().stream()
                .filter(table -> table.getNumber() == tableNumber)
                .count();

        if (count <= 0) {
            throw new IllegalArgumentException(
                    String.format("입력한 테이블 번호 : %d - 해당 테이블 번호는 존재하지 않습니다.", tableNumber));
        }
    }
}
