package com.javabom.cafe.controller.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleDto {

    private Long salesId;

    private String tableName;

    private LocalDateTime date;

    private double price;

    public static Builder builder() {
        return new Builder();
    }

    public SaleDto(final Builder builder) {
        this.salesId = builder.salesId;
        this.tableName = builder.tableName;
        this.date = builder.date;
        this.price = builder.price;
    }

    public Long getSalesId() {
        return salesId;
    }

    public String getTableName() {
        return tableName;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return formatter.format(date);
    }

    public double getPrice() {
        return price;
    }


    public static class Builder {

        private Long salesId;

        private String tableName;

        private LocalDateTime date;

        private double price;


        public Builder salesId(final Long salesId) {
            this.salesId = salesId;
            return this;
        }

        public Builder tableName(final String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder price(final double price) {
            this.price = price;
            return this;
        }

        public SaleDto build() {
            return new SaleDto(this);
        }
    }
}
