package domain.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableRepositoryTest {

    @DisplayName("존재하지 않는 테이블 번호면 NoSuchElementException을 발생시킨다.")
    @Test
    void find() {
        //given
        int tableNumber = 7;

        //then
        assertThatThrownBy(() -> TableRepository.find(tableNumber))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", tableNumber));
    }
}