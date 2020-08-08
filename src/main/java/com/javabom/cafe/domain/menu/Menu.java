package com.javabom.cafe.domain.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MENUS")
@NoArgsConstructor
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Enumerated(value = EnumType.STRING)
    private MenuType menuType;

    @Builder
    public Menu(final Long id, final String name, final MenuType menuType, final int price) {
        this.id = id;
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public int getMultiplyPriceOf(final int quantity) {
        return this.price * quantity;
    }

    public boolean equalsOf(String name) {
        return this.name.equals(name);
    }

    public boolean equalsOf(MenuType menuType) {
        return this.menuType.equals(menuType);
    }

    public boolean equalsOf(final int number) {
        return this.id == number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        final Menu menu = (Menu) o;
        return id == menu.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return menuType + " " + id + " - " + name + " : " + price + "원";
    }
}
