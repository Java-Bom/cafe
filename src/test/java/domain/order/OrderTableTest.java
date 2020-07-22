package domain.order;

import domain.table.Table;
import domain.table.TableRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTableTest {

    @DisplayName("같은 테이블이면 true, 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 1, true", "1, 2, false"})
    void equalsBy(int myTable, int inputTable, boolean expected) {
        //given
        OrderTable orderTable = new OrderTable(TableRepository.find(myTable));
        Table table = TableRepository.find(inputTable);

        //then
        assertThat(orderTable.equalsBy(table)).isEqualTo(expected);
    }

    @DisplayName("주문 목록이 비어있으면 IllegalArgumentException을 발생시킨다.")
    @Test
    void checkOrderEmpty() {
        //given
        Table table = TableRepository.find(1);
        OrderTable orderTable = new OrderTable(table);

        //then
        assertThatThrownBy(() -> orderTable.calculateFee(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("테이블 번호: %s, 주문한 기록이 없습니다.", table.toString()));
    }
}