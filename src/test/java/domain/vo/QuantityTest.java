package domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.vo.Quantity.MAX_QUANTITY;
import static domain.vo.Quantity.MIN_QUANTITY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {

    @DisplayName("수량이 0-30이 아니면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 31})
    void checkRange(int quantity) {
        //then
        assertThatThrownBy(() -> Quantity.of(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("수량: %d, %d-%d 수량만 가능합니다.", quantity, MIN_QUANTITY, MAX_QUANTITY));
    }
}