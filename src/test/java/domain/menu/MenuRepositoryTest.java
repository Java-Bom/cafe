package domain.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MenuRepositoryTest {

    @Test
    void menuNumberValidTest() {
        assertThatThrownBy(() -> MenuRepository.getMenuByNumber(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 메뉴 번호 : 10 - 입력하신 번호에 해당되는 메뉴는 존재하지않습니다.");
    }
}