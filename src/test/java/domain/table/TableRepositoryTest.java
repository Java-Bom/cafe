package domain.table;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TableRepositoryTest {

    @Test
    public void tableNumberValidTest() {
        int tableNumber = 10;
        assertThatThrownBy(() -> TableRepository.getTable(tableNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 테이블 번호 : %d - 해당 테이블 번호는 존재하지 않습니다.", tableNumber);
    }
}