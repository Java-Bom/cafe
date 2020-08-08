package com.javabom.cafe.dto.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuCreateReqDto {
    private String name;
    private int price;
    private String menuType;
}
