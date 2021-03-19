package com.javabom.cafe.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderPayDto {
    private double cash;
    private double card;

    @Builder
    public OrderPayDto(final double cash, final double card) {
        this.cash = cash;
        this.card = card;
    }
}
