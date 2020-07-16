package domain.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Category {
    CAKE("케이크"),
    BEVERAGE("음료"),
    EMPTY("");

    private final String name;

    Category(final String name) {
        this.name = name;
    }

    public static Category findByName(String name) {
        return Arrays.stream(values())
                .filter(category -> category.equalsOf(name))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("이름: %s, 존재하지 않는 이름입니다.", name)));
    }

    private boolean equalsOf(final String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
