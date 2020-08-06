package com.javabom.cafe.controller;

import com.javabom.cafe.dto.order.OrderAddDto;
import com.javabom.cafe.dto.order.TableOrderDto;
import com.javabom.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{id}")
    public TableOrderDto showOrders(@PathVariable final Long id) {
        return orderService.showOrders(id);
    }

    @PostMapping("")
    public ResponseEntity<Void> addOrder(@RequestBody OrderAddDto orderAddDto) {
        System.out.println(orderAddDto);
        orderService.addOrder(orderAddDto);

        return ResponseEntity.ok().build();
    }
}
