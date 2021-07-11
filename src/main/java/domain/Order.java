package domain;

public class Order {
    private final int menuNum;
    private final int tableNum;

    public Order(int menuNum, int tableNum) {
        this.menuNum = menuNum;
        this.tableNum = tableNum;
    }

    public int getMenuNum() {
        return this.menuNum;
    }

    public int getTableNum() {
        return tableNum;
    }


}
