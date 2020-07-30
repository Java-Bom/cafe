package com.javabom.cafe.domain.table;

import com.javabom.cafe.domain.BaseEntity;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.vo.Amount;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
public class CafeTable extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String tableName;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @OneToMany(mappedBy = "table")
    private List<OrderMenu> orderMenus = new ArrayList<>();

    public CafeTable() {
    }

    private CafeTable(final String tableName) {
        this.tableName = tableName;
        tableStatus = TableStatus.EMPTY;
    }

    public static CafeTable ofName(final String tableName) {
        return new CafeTable(tableName);
    }

    public void addMenu(final OrderMenu orderMenu) {
        if (containsOrderMenu(orderMenu)) {
            OrderMenu previousMenu = findOrderMenu(orderMenu);
            previousMenu.extraOrder(orderMenu);
            return;
        }

        changeTableStatus(TableStatus.ORDER);
        orderMenus.add(orderMenu);
    }

    private boolean containsOrderMenu(final OrderMenu newOrderMenu) {
        return orderMenus.stream()
                .anyMatch(orderMenu -> orderMenu.isSameMenu(newOrderMenu));
    }

    public void changeTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }

    private OrderMenu findOrderMenu(final OrderMenu orderMenu) {
        return orderMenus.stream()
                .filter(previousMenu -> previousMenu.isSameMenu(orderMenu))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 메뉴는 주문한 적이 없습니다."));
    }

    public Amount getTotalAmount() {
        Amount amount = Amount.valueOf(0);

        for (OrderMenu orderMenu : orderMenus) {
            amount = amount.add(orderMenu.getTotalAmount());
        }

        return amount;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public Long getId() {
        return id;
    }

    public String getTableName() {
        return tableName;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    @Override
    public String toString() {
        return tableName;
    }

    public boolean isSameName(final String newTableName) {
        return tableName.equals(newTableName);
    }
}
