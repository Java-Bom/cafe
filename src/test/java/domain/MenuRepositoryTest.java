package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuRepositoryTest {

    @DisplayName("선택한 menu 숫자에 해당하는 Menu를 반환한다")
    @Test
    void pick() {
        Menu menu = MenuRepository.findByNumber(1);
        assertThat(menu).isEqualTo(new Menu(1, "가나슈", Category.CAKE, 7000));
    }
}