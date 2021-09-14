package domain;

public class Order {
	private final int tableNumber;
	private final int menuNumber;
	private final int menuCount;

	public Order(int tableNumber, int menuNumber, int menuCount) {
		this.tableNumber = tableNumber;
		this.menuNumber = menuNumber;
		this.menuCount = menuCount;
	}

	public int getMenuNumber() {
		return this.menuNumber;
	}

	public int getTableNumber() {
		return this.tableNumber;
	}

	public int getMenuCount() {
		return this.menuCount;
	}

	public boolean isEqualTable(int tableNumber) {
		return this.tableNumber == tableNumber;
	}

	public boolean isEqualMenuNumber(int menuNumber) {
		return this.menuNumber == menuNumber;
	}
}
