package com.javabom.cafe.controller;

import com.javabom.cafe.dto.menu.MenuCreateReqDto;
import com.javabom.cafe.dto.menu.MenuFindAllResDto;
import com.javabom.cafe.dto.menu.MenuFindAllTypeResDto;
import com.javabom.cafe.dto.menu.MenuFindResDto;
import com.javabom.cafe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menus")
public class MenuController {

    private final MenuService menuService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MenuFindResDto> find(@PathVariable Long id) {
        return menuService.find(id);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<String> create(@RequestBody MenuCreateReqDto reqDto) {
        return menuService.create(reqDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return menuService.delete(id);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<MenuFindAllResDto>> findAll() {
        return menuService.findAll();
    }

    @GetMapping(value = "/type")
    public ResponseEntity<List<MenuFindAllTypeResDto>> findAllType() {
        return menuService.findAllType();
    }
}
