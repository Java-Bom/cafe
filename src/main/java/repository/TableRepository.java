package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.Table;

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

	//nubmer에 해당하는 Table을 return한다.
	public static Optional<Table> findByNumber(int number) {
		return Optional.ofNullable(tables.stream()
			.filter(table -> table.isEqualNumber(number))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
				number + "번 테이블은 존재하지 않는 테이블입니다.")));//Optional로 감싸줌으로써, Null이더라도 반환해서 클라이언트 단에서 처리해줄 수 있도록
	}

	public static List<Table> getTables() {
		return Collections.unmodifiableList(tables);
	}
}
