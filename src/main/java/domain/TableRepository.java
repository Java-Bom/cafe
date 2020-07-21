package domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
//    private static final List<Table> tables = new ArrayList<>();
//
//    static {
//        tables.add(new Table(1));
//        tables.add(new Table(2));
//        tables.add(new Table(3));
//        tables.add(new Table(5));
//        tables.add(new Table(6));
//        tables.add(new Table(8));
//    }
//
//    public static List<Table> tables() {
//        return Collections.unmodifiableList(tables);
//    }
}
