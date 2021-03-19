package cafe.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuRepositoryTest {

    @DisplayName("선택한 menu 숫자에 해당하는 Menu를 반환한다")
    @Test
    void pick() {
        cafe.domain.Menu menu = cafe.domain.MenuRepository.findByNumber(1);
        assertThat(menu).isEqualTo(new cafe.domain.Menu(1, "가나슈", cafe.domain.Category.CAKE, 7000));
    }
}