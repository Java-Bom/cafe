package cafe.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableRepository {
    private static final List<cafe.domain.Table> tables = new ArrayList<>();

    static {
        tables.add(new cafe.domain.Table(1));
        tables.add(new cafe.domain.Table(2));
        tables.add(new cafe.domain.Table(3));
        tables.add(new cafe.domain.Table(5));
        tables.add(new cafe.domain.Table(6));
        tables.add(new cafe.domain.Table(8));
    }

    public static List<cafe.domain.Table> tables() {
        return Collections.unmodifiableList(tables);
    }
}
