package com.javabom.cafe.service;

import com.javabom.cafe.domain.order.OrderMenuRepository;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.table.TableRepository;
import com.javabom.cafe.dto.table.TableAddDto;
import com.javabom.cafe.dto.table.TableInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final OrderMenuRepository orderMenuRepository;

    public List<TableInfoDto> getTableInfos() {
        List<Table> tables = tableRepository.findAll();

        return tables.stream()
                .map(table -> new TableInfoDto(table.getId(), table.getTableName(), orderMenuRepository.existsByTable(table)))
                .collect(Collectors.toList());
    }

    public Table addTable(final TableAddDto tableAddDto) {
        return tableRepository.save(tableAddDto.toEntity());
    }

    public void deleteTable(final Long id) {
        tableRepository.deleteById(id);
    }
}
