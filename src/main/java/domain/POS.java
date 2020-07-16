package domain;

import domain.enums.PayType;
import domain.menu.Menus;
import domain.table.Table;
import domain.table.TableRepository;
import domain.vo.Money;

import java.util.*;
import java.util.stream.Collectors;

public class POS {
    private final Map<Table, Menus> tables;

    public POS() {
        this.tables = createTables();
    }

    private Map<Table, Menus> createTables() {
        return TableRepository.findAll().stream()
                .collect(Collectors.toMap(table -> table, table -> new Menus()));
    }

    public void takeOrder(final int tableNumber, final int menuIdx, final int quantity) {
        Table table = TableRepository.find(tableNumber);
        tables.get(table).addMenu(menuIdx, quantity);
    }

    public Menus findOrderHistoryOf(final int tableNumber) {
        return tables.get(TableRepository.find(tableNumber));
    }

    public Money calculateFee(final int tableNumber, final int paymentMethod) {
        Menus menus = tables.get(TableRepository.find(tableNumber));
        checkMenuEmpty(menus, tableNumber);
        return PayType.findByType(paymentMethod)
                .calculateDiscountFee(menus);
    }

    private void checkMenuEmpty(final Menus menus, final int tableNumber) {
        if (menus.isEmpty()) {
            throw new IllegalArgumentException(String.format("테이블 번호: %d, 주문한 기록이 없습니다.", tableNumber));
        }
    }

    public void clearOf(final int tableNumber) {
        tables.put(TableRepository.find(tableNumber), new Menus());
    }

    public Map<Table, Menus> get() {
        return Collections.unmodifiableMap(tables);
    }
}
