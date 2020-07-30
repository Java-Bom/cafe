package com.javabom.cafe.controller.dto;

import com.javabom.cafe.domain.payment.PaymentType;
import com.javabom.cafe.domain.vo.Amount;

import java.util.Date;

public class NewPaymentDto {

    private Long tableId;

    private String paymentType;

    public NewPaymentDto() {
    }

    public NewPaymentDto(final Long tableId, final String paymentType) {
        this.tableId = tableId;
        this.paymentType = paymentType;
    }

    public Long getTableId() {
        return tableId;
    }

    public String getPaymentType() {
        return paymentType;
    }

}

