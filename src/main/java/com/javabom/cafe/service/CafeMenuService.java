package com.javabom.cafe.service;

import com.javabom.cafe.controller.dto.MenuTypeDto;
import com.javabom.cafe.controller.dto.MenuDto;
import com.javabom.cafe.controller.dto.NewMenuDto;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.repository.CafeMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CafeMenuService {

    @Autowired
    private CafeMenuRepository menuRepository;

    public List<MenuDto> getMenus() {
        List<Menu> menus = menuRepository.findAll();

        return menus.stream()
                .map(menu -> MenuDto.builder()
                                .menuId(menu.getId())
                                .menuName(menu.getMenuName())
                                .menuType(menu.getMenuType().name())
                                .price(menu.getPrice().getValue())
                                .build())
                .collect(Collectors.toList());
    }

    public MenuDto getMenu(final Long id) {
        try {
            Menu menu = menuRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("요청한 MenuId : %d - 존재하지 않는 메뉴입니다.", id)));

            return MenuDto.builder()
                    .menuId(menu.getId())
                    .menuName(menu.getMenuName())
                    .menuType(menu.getMenuType().name())
                    .price(menu.getPrice().getValue())
                    .build();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new MenuDto();
        }
    }

    public List<MenuTypeDto> getCategories() {
        return Arrays.stream(MenuType.values())
                .map(category -> new MenuTypeDto(category.getMenuTypeId(), category.name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(final NewMenuDto newMenuDto) {
        try {
            String TypeName = newMenuDto.getMenuType();
            MenuType type = MenuType.findByName(TypeName);
            Menu menu = new Menu(newMenuDto.getMenuName(), type, newMenuDto.getMenuPrice());

            menuRepository.save(menu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void deleteMenu(final Long id) {
        menuRepository.deleteById(id);
    }
}
