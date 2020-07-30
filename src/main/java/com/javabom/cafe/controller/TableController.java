package com.javabom.cafe.controller;

import com.javabom.cafe.controller.dto.NewTableDto;
import com.javabom.cafe.controller.dto.TableDto;
import com.javabom.cafe.service.CafeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController {

    @Autowired
    private CafeTableService tableService;

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public List<TableDto> getTables() {
        return tableService.getTables();
    }


    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public void createTable(@RequestBody final NewTableDto newTableDto) {
        tableService.createTable(newTableDto);
    }

    @RequestMapping(value = "/tables/{id}", method = RequestMethod.DELETE)
    public void deleteTable(@PathVariable("id") final Long id) {
        tableService.deleteTable(id);
    }
}
