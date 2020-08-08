package com.javabom.cafe.controller;

import com.javabom.cafe.dto.order.OrderCreateReqDto;
import com.javabom.cafe.dto.order.OrderFindAllResDto;
import com.javabom.cafe.dto.table.FindAllResDto;
import com.javabom.cafe.dto.table.TableCreateReqDto;
import com.javabom.cafe.dto.table.TableGetResDto;
import com.javabom.cafe.dto.table.TableAmountResDto;
import com.javabom.cafe.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tables")
public class TableController {

    private final TableService tableService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TableGetResDto> get(@PathVariable("id") Long id) {
        return tableService.get(id);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<String> create(@RequestBody TableCreateReqDto reqDto) {
        return tableService.create(reqDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return tableService.delete(id);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<FindAllResDto>> findAll() {
        return tableService.findAll();
    }

    @PostMapping(value = "/{id}/order")
    public ResponseEntity<String> addOrder(@PathVariable("id") Long id,
                                           @RequestBody OrderCreateReqDto reqDto) {
        return tableService.addOrder(id, reqDto);
    }

    @GetMapping(value = "/{id}/orders")
    public ResponseEntity<List<OrderFindAllResDto>> findOrders(@PathVariable("id") Long id) {
        return tableService.findOrders(id);
    }

    @GetMapping(value = "{id}/amounts")
    public ResponseEntity<TableAmountResDto> getAmounts(@PathVariable("id") Long id) {
        return tableService.getAmounts(id);
    }
}
