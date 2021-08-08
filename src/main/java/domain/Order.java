package domain;

public class Order {
    private final int menuNumber;
    private final int tableNumber;

    public Order(int menuNum, int tableNum) {
        this.menuNumber = menuNum;
        this.tableNumber = tableNum;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isEqualTable(int tableNumber){
        return this.tableNumber == tableNumber;
    }

    public boolean isEqualMenuNumber(int menuNumber){
        return this.menuNumber==menuNumber;
    }
}
