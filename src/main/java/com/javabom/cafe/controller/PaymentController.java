package com.javabom.cafe.controller;

import com.javabom.cafe.controller.dto.NewPaymentDto;
import com.javabom.cafe.controller.dto.PaymentPriceDto;
import com.javabom.cafe.controller.dto.SaleDto;
import com.javabom.cafe.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void payment(@RequestBody final NewPaymentDto newPaymentDto) {
        paymentService.pay(newPaymentDto);
    }

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public List<SaleDto> getSalesList() {
        return paymentService.getPaymentList();
    }

    @RequestMapping(value = "/payment/{tableId}", method = RequestMethod.GET)
    public PaymentPriceDto getPaymentPrice(@PathVariable("tableId") final Long tableId) {
        return paymentService.getPaymentPrice(tableId);
    }
}
