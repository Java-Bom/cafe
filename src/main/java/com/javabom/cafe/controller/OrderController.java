package com.javabom.cafe.controller;

import com.javabom.cafe.dto.order.TableOrderDto;
import com.javabom.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{id}")
    public TableOrderDto showOrders(@PathVariable final Long id) {
        return orderService.showOrders(id);
    }
}
