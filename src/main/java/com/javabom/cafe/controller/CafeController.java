package com.javabom.cafe.controller;


import com.javabom.cafe.service.CafeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping("/table")
    public ResponseEntity createTable(String tableName) {
        cafeService.createTable(tableName);
        return ResponseEntity.ok(null);
    }


}
