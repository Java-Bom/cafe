package domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MenuRepositoryTest {

    @DisplayName("존재하지 않는 메뉴 번호면 NoSuchElementException을 발생시킨다.")
    @Test
    void find() {
        //given
        int menuIdx = 7;

        //then
        assertThatThrownBy(() -> MenuRepository.find(menuIdx))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("메뉴번호: %d, 존재하지 않는 메뉴번호입니다.", menuIdx));
    }
}