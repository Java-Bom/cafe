package com.javabom.cafe.dto.table;

import com.javabom.cafe.domain.table.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TableAddDto {
    private String tableName;

    @Builder
    public TableAddDto(final String tableName) {
        this.tableName = tableName;
    }

    public Table toEntity() {
        return Table.builder()
                .tableName(this.tableName)
                .build();
    }
}
