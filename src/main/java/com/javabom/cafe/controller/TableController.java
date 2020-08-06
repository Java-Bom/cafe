package com.javabom.cafe.controller;

import com.javabom.cafe.dto.table.TableAddDto;
import com.javabom.cafe.dto.table.TableInfoDto;
import com.javabom.cafe.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("")
    public List<TableInfoDto> showTableList() {
        List<TableInfoDto> tableInfos = tableService.getTableInfos();
        return tableInfos;
    }

    @PostMapping("")
    public ResponseEntity<Void> addTable(@RequestBody final TableAddDto tableAddDto) {
        tableService.addTable(tableAddDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable final Long id) {
        tableService.deleteTable(id);

        return ResponseEntity.ok().build();
    }
}
