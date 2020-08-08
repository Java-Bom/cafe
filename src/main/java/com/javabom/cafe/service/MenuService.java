package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuRepository;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.dto.menu.MenuCreateReqDto;
import com.javabom.cafe.dto.menu.MenuFindAllResDto;
import com.javabom.cafe.dto.menu.MenuFindAllTypeResDto;
import com.javabom.cafe.dto.menu.MenuFindResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public ResponseEntity<MenuFindResDto> find(final Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("메뉴번호: %d, 존재하지 않는 메뉴번호입니다.", id)));

        MenuFindResDto resDto = new MenuFindResDto(menu);
        return ResponseEntity.ok(resDto);
    }

    @Transactional
    public ResponseEntity<String> create(final MenuCreateReqDto reqDto) {
        Menu menu = Menu.builder()
                .name(reqDto.getName())
                .menuType(MenuType.findByName(reqDto.getMenuType()))
                .price(reqDto.getPrice())
                .build();

        menuRepository.save(menu);

        return ResponseEntity.ok("메뉴 생성");
    }

    @Transactional
    public ResponseEntity<String> delete(final Long id) {
        menuRepository.deleteById(id);

        return ResponseEntity.ok("메뉴 삭제");
    }

    public ResponseEntity<List<MenuFindAllResDto>> findAll() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuFindAllResDto> resDtos = menus.stream()
                .map(menu -> new MenuFindAllResDto(menu.getId(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getMenuType()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDtos);
    }

    public ResponseEntity<List<MenuFindAllTypeResDto>> findAllType() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuFindAllTypeResDto> resDtos = menus.stream()
                .map(menu -> new MenuFindAllTypeResDto(menu.getMenuType().getId(), menu.getMenuType().getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDtos);
    }
}
