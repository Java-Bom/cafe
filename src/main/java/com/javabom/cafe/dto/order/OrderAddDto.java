package com.javabom.cafe.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderAddDto {
    private Long tableId;
    private Long menuId;
    private int quantity;
}
