package com.javabom.cafe.dto.menu;

import com.javabom.cafe.domain.menu.Category;
import com.javabom.cafe.domain.menu.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuAddDto {
    private Category menuType;
    private String menuName;
    private int menuPrice;

    public Menu toEntity() {
        return Menu.builder()
                .name(menuName)
                .category(menuType)
                .price(menuPrice)
                .build();
    }
}
