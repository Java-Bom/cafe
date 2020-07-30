package domain.order;

import domain.menu.Menu;
import domain.table.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
public class OrderMenu {
    private static final int MAX_QUANTITY = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "menu_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @JoinColumn(name = "table_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Table table;

    @Embedded
    @Getter
    private Quantity quantity;

    @Column
    private boolean paymentStatus = false;

    @Builder
    public OrderMenu(final Menu menu, final Table table, final Quantity quantity) {
        validateMaxQuantity(quantity);
        this.menu = menu;
        this.table = table;
        this.quantity = quantity;
    }

    public boolean isSameMenu(final Menu menu) {
        return this.menu.equals(menu);
    }

    public void pay() {
        paymentStatus = true;
    }

    private void validateMaxQuantity(final Quantity quantity) {
        if (quantity.getValue() > MAX_QUANTITY) {
            throw new IllegalArgumentException("주문 가능 수량을 초과 하였습니다. - " + quantity);
        }
    }
}
