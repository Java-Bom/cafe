package repository;

import domain.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Optional<Table> findByNumber(int number) {
        return Optional.ofNullable(tables.get(number)); //Optional로 감싸줌으로써, Null이더라도 반환해서 클라이언트 단에서 처리해줄 수 있도록
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }
}
