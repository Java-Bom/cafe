package domain.menu;

import domain.vo.Amount;

public class Menu {
    private final int number;
    private final String name;
    private final Category category;
    private final Amount amount;

    public Menu(final int number, final String name, final Category category, final int price) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.amount = Amount.valueOf(price);
    }

    public int getNumber() {
        return number;
    }

    public Amount getAmount() {
        return amount.getAmount();
    }

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + amount + "원";
    }
}
