package dto.table;

import lombok.Builder;

public class TableInfoDto {
    private Long tableId;
    private String tableName;
    private boolean orderStatus;

    @Builder
    public TableInfoDto(final String tableName, final boolean orderStatus) {
        this.tableName = tableName;
        this.orderStatus = orderStatus;
    }
}
