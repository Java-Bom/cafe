package com.javabom.cafe.controller;


import com.javabom.cafe.dtos.CreateTableDto;
import com.javabom.cafe.dtos.ShownTable;
import com.javabom.cafe.service.CafeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping("/table")
    public ResponseEntity createTable(@RequestBody CreateTableDto dto) {
        cafeService.createTable(dto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tables")
    public ResponseEntity<List<ShownTable>> showAllTables() {
        List<ShownTable> tables = cafeService.findAllTables();
        return ResponseEntity.ok(tables);
    }


}
