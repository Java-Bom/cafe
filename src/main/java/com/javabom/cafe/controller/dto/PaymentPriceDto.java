package com.javabom.cafe.controller.dto;

public class PaymentPriceDto {

    private double cashPrice;

    private double cardPrice;

    public PaymentPriceDto() {
    }

    public PaymentPriceDto(final double cashPrice, final double cardPrice) {
        this.cashPrice = cashPrice;
        this.cardPrice = cardPrice;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public double getCardPrice() {
        return cardPrice;
    }
}
