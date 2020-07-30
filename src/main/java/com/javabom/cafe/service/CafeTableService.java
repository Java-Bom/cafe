package com.javabom.cafe.service;

import com.javabom.cafe.controller.dto.NewTableDto;
import com.javabom.cafe.controller.dto.TableDto;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.repository.CafeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CafeTableService {

    @Autowired
    private CafeTableRepository cafeTableRepository;

    public List<TableDto> getTables() {
        List<CafeTable> tables = cafeTableRepository.findAll();
        return tables.stream()
                .map(table -> new TableDto(table.getId(), table.getTableName(), table.getTableStatus().name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createTable(final NewTableDto newTableDto) {
        try {
            String newTableName = newTableDto.getTableName();
            validTableName(newTableName);

            CafeTable cafeTable = CafeTable.ofName(newTableName);
            cafeTableRepository.save(cafeTable);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validTableName(final String newTableName) {
        List<CafeTable> allTables = cafeTableRepository.findAll();
        boolean hasSameTableName = allTables.stream()
                .anyMatch(cafeTable -> cafeTable.isSameName(newTableName));

        if (hasSameTableName) {
            throw new IllegalArgumentException(
                    String.format("입력한 테이블 이름 : %s - 중복된 테이블 이름이 존재합니다.", newTableName));
        }
    }

    @Transactional
    public void deleteTable(final Long id) {
        cafeTableRepository.deleteById(id);
    }
}
