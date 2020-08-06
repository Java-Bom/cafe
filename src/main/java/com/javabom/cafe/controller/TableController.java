package com.javabom.cafe.controller;

import com.javabom.cafe.dto.table.TableAddDto;
import com.javabom.cafe.dto.table.TableInfoDto;
import com.javabom.cafe.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

        System.out.println(tableInfos);

        return tableInfos;
    }

    @PostMapping("")
    public ResponseEntity<Void> addTable(@RequestBody final TableAddDto tableAddDto) {
        tableService.addTable(tableAddDto);

        return ResponseEntity.ok().build();
    }
}
