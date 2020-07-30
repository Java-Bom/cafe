package com.javabom.cafe.controller;

import com.javabom.cafe.controller.dto.NewOrderDto;
import com.javabom.cafe.controller.dto.OrdersDto;
import com.javabom.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void orderMenu(@RequestBody final NewOrderDto newOrderDto) {
        orderService.createOrder(newOrderDto);
    }

    @RequestMapping(value = "/order/{tableId}", method = RequestMethod.GET)
    public OrdersDto getOrderMenus(@PathVariable("tableId") final Long tableId) {
        return orderService.getOrderMenus(tableId);
    }
}
