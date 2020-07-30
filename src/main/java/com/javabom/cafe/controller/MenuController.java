package com.javabom.cafe.controller;

import com.javabom.cafe.controller.dto.MenuTypeDto;
import com.javabom.cafe.controller.dto.MenuDto;
import com.javabom.cafe.controller.dto.NewMenuDto;
import com.javabom.cafe.service.CafeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private CafeMenuService menuService;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public List<MenuDto> getMenus() {
        return menuService.getMenus();
    }

    @RequestMapping(value = "/menuType", method = RequestMethod.GET)
    public List<MenuTypeDto> getCategories() {
        return menuService.getCategories();
    }

    @RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
    public MenuDto getMenu(@PathVariable("id") final Long id) {
        return menuService.getMenu(id);
    }

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public void createMenu(@RequestBody final NewMenuDto newMenuDto) {
        menuService.create(newMenuDto);
    }

    @RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
    public void deleteMenu(@PathVariable("id") final Long id) {
        menuService.deleteMenu(id);
    }
}
