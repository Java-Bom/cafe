package com.javabom.cafe.dto.order;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.vo.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateReqDto {
    private Menu menu;
    private Quantity quantity;
}
