package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.repository.CafeMenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CafeMenuServiceTest {

    @Autowired
    CafeMenuRepository cafeMenuRepository;

    @Test
    @DisplayName("생성된 메뉴들을 모두 가져오는지 확인.")
    void getMenusTest() {
        Menu menu1 = new Menu("latte", MenuType.BEVERAGE, 4000);
        Menu menu2 = new Menu("choco", MenuType.CAKE, 7000);
        List<Menu> menus = Arrays.asList(menu1, menu2);

        cafeMenuRepository.save(menu1);
        cafeMenuRepository.save(menu2);

        List<Menu> findMenus = cafeMenuRepository.findAll();


        assertThat(findMenus).isEqualTo(menus);
    }

    @Test
    @DisplayName("primary key 값으로 메뉴를 조회되는지 테스트.")
    void getMenuTest() {
        Menu menu = new Menu(1L, "latte", 3000);
        cafeMenuRepository.save(menu);

        Menu findMenuById = cafeMenuRepository.findById(1L).get();

        assertThat(findMenuById).isEqualTo(menu);
    }

    @Test
    @DisplayName("메뉴를 실제 생성하는지 확인")
    void createTest() {
        Menu menu = new Menu("latte", MenuType.BEVERAGE, 4000);
        cafeMenuRepository.save(menu);

        Menu findMenu = cafeMenuRepository.findById(menu.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 메뉴입니다."));

        assertThat(findMenu).isEqualTo(menu);
    }

    @Test
    @DisplayName("기존에 있던 메뉴를 삭제하는지 확인.")
    void deleteMenuTest() {
        Menu menu1 = new Menu("latte", MenuType.BEVERAGE, 4000);
        Menu menu2 = new Menu("choco", MenuType.CAKE, 7000);

        cafeMenuRepository.save(menu1);
        cafeMenuRepository.save(menu2);

        cafeMenuRepository.delete(menu1);
        List<Menu> menus = cafeMenuRepository.findAll();

        assertThat(menus.contains(menu1)).isFalse();
    }
}