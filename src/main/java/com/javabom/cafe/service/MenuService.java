package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Category;
import com.javabom.cafe.domain.menu.MenuRepository;
import com.javabom.cafe.dto.menu.CategoryInfoDto;
import com.javabom.cafe.dto.menu.MenuAddDto;
import com.javabom.cafe.dto.menu.MenuInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public List<MenuInfoDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menu -> new MenuInfoDto(menu.getId(), menu.getName(), menu.getPrice(), menu.getCategory().name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMenu(final Long id) {
        menuRepository.deleteById(id);
    }

    @Transactional
    public void addMenu(final MenuAddDto menuAddDto) {
        menuRepository.save(menuAddDto.toEntity());
    }

    public List<CategoryInfoDto> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(category -> new CategoryInfoDto(category.getCategoryId(), category.name()))
                .collect(Collectors.toList());
    }
}
