package com.javabom.cafe.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoDto {
    private Long menuId;
    private String menuName;
    private int price;
    private String type;
}
