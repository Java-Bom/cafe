package com.javabom.cafe.domain.payment;

import com.javabom.cafe.domain.BaseEntity;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.table.TableStatus;
import com.javabom.cafe.domain.vo.Amount;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Payment extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CafeTable cafeTable;

    @Enumerated
    private PaymentType paymentType;

    @Embedded
    private Amount paymentAmount;

    public Payment() {
    }

    public Payment(final CafeTable cafeTable, final PaymentType paymentType, final Amount paymentAmount) {
        this.cafeTable = cafeTable;
        cafeTable.changeTableStatus(TableStatus.EMPTY);
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
    }


    public Long getId() {
        return id;
    }

    public CafeTable getCafeTable() {
        return cafeTable;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

}
