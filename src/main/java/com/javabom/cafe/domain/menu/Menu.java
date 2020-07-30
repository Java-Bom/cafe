package com.javabom.cafe.domain.menu;

import com.javabom.cafe.domain.vo.Amount;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    private String menuName;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    @Embedded
    private Amount price;

    public Menu() {
    }

    public Menu(final Long id, final String menuName, final double price) {
        this.id = id;
        this.menuName = menuName;
        this.price = Amount.valueOf(price);
    }
    public Menu(final String menuName, final MenuType menuType, final double price) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.price = Amount.valueOf(price);
    }

    public Long getId() {
        return id;
    }

    public Amount getPrice() {
        return price;
    }

    public boolean isSameCategory(final MenuType menuType) {
        return this.menuType.equals(menuType);
    }

    public Amount getTotalAmount(final int value) {
        return price.multiplyValue(value);
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Menu menu = (Menu) o;
        return Objects.equals(getId(), menu.getId()) &&
                Objects.equals(getMenuName(), menu.getMenuName()) &&
                getMenuType() == menu.getMenuType() &&
                Objects.equals(getPrice(), menu.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMenuName(), getMenuType(), getPrice());
    }
}
