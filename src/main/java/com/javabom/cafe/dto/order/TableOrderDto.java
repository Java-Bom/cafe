package com.javabom.cafe.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TableOrderDto {
    private Long tableId;
    private String tableName;
    private List<OrdersDto> orders;
    private OrderPayDto pay;

    @Builder
    public TableOrderDto(final Long tableId, final String tableName, final List<OrdersDto> ordersDtos) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.orders = ordersDtos;
    }

    public TableOrderDto(final Long tableId, final String tableName, final List<OrdersDto> orders, final OrderPayDto pay) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.orders = orders;
        this.pay = pay;
    }
}
