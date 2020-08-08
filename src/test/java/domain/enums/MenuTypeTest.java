package domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTypeTest {

    @DisplayName("카테고리 이름이 케이크면 CAKE, 음료면 BEVERAGE를 반환한다.")
    @ParameterizedTest
    @CsvSource({"케이크, CAKE", "음료, BEVERAGE"})
    void findByName(String name, Category expected) {
        //when
        Category actual = Category.findByName(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("존재 하지 않는 이름이면 NoSuchElementException을 발생시킨다.")
    @Test
    void findByNameError(){
        //given
        String name = "볶음밥";

        //then
        assertThatThrownBy(() -> Category.findByName(name))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("이름: %s, 존재하지 않는 이름입니다.", name));
    }
}