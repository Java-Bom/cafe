package com.javabom.cafe.controller;

import com.javabom.cafe.dto.menu.CategoryInfoDto;
import com.javabom.cafe.dto.menu.MenuAddDto;
import com.javabom.cafe.dto.menu.MenuInfoDto;
import com.javabom.cafe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("")
    public List<MenuInfoDto> showMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("categories")
    public List<CategoryInfoDto> showCategories() {
        return menuService.getAllCategories();
    }

    @PostMapping("")
    public ResponseEntity<Void> addMenu(@RequestBody final MenuAddDto menuAddDto) {
        menuService.addMenu(menuAddDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable final Long id) {
        menuService.deleteMenu(id);

        return ResponseEntity.ok().build();
    }
}
