package com.javabom.cafe.dto.table;

import com.javabom.cafe.domain.vo.Money;

public class TableAmountResDto {
    private final Money cashPrice;
    private final Money cardPrice;

    public TableAmountResDto(final Money cashPrice, final Money cardPrice) {
        this.cashPrice = cashPrice;
        this.cardPrice = cardPrice;
    }
}
