package com.javabom.cafe.controller.dto;

public class MenuDto {

    private Long menuId;

    private String menuName;

    private String menuType;

    private double price;

    public MenuDto() {
    }

    private MenuDto(final Builder builder) {
        this.menuId = builder.menuId;
        this.menuName = builder.menuName;
        this.menuType = builder.menuType;
        this.price = builder.price;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public double getPrice() {
        return price;
    }


    public static class Builder {

        private Long menuId;

        private java.lang.String menuName;

        private String menuType;

        private double price;

        private Builder() {

        }

        public Builder menuId(final Long menuId) {
            this.menuId = menuId;
            return this;
        }

        public Builder menuName(final java.lang.String menuName) {
            this.menuName = menuName;
            return this;
        }

        public Builder menuType(final String menuType) {
            this.menuType = menuType;
            return this;
        }

        public Builder price(final double price) {
            this.price = price;
            return this;
        }

        public MenuDto build() {
            return new MenuDto(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
