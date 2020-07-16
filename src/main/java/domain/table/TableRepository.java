package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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

    public static List<Table> findAll() {
        return Collections.unmodifiableList(tables);
    }

    public static Table find(final int tableNumber) {
        return tables.stream()
                .filter(table -> table.equalsOf(tableNumber))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", tableNumber)));
    }
}
