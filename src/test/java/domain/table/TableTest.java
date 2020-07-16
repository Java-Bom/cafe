package domain.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class TableTest {

    @DisplayName("테이블의 번호와 일치하면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 1, true", "1, 2, false"})
    void equalsOf(int tableNumber, int number, boolean expected) {
        //given
        Table table = TableRepository.find(tableNumber);

        //then
        assertThat(table.equalsOf(number)).isEqualTo(expected);
    }
}