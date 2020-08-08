package com.javabom.cafe.dto.menu;

import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.menu.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuFindResDto {

    private Long id;
    private String name;
    private int price;
    private MenuType menuType;

    public MenuFindResDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.menuType = menu.getMenuType();
    }
}
