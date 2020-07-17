package domain.table;

import java.util.ArrayList;
import java.util.List;

public class Tables {

    private final List<Table> tables = new ArrayList<>();

    public void addTable(Table table) {
        tables.add(table);
    }

    public boolean contains(final Table table) {
        return tables.contains(table);
    }

    public void removeTable(final Table table) {
        tables.remove(table);
    }
}
