package com.javabom.cafe.dto.table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TableInfoDto {
    private Long tableId;
    private String tableName;
    private boolean orderStatus;

    @Builder
    public TableInfoDto(final Long tableId, final String tableName, final boolean orderStatus) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.orderStatus = orderStatus;
    }
}
