package com.javabom.cafe.service;

import com.javabom.cafe.controller.dto.NewPaymentDto;
import com.javabom.cafe.controller.dto.PaymentPriceDto;
import com.javabom.cafe.controller.dto.SaleDto;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.payment.Payment;
import com.javabom.cafe.domain.payment.PaymentType;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.repository.CafeTableRepository;
import com.javabom.cafe.repository.OrderRepository;
import com.javabom.cafe.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    @Autowired
    private CafeTableRepository tableRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void pay(final NewPaymentDto newPaymentDto) {
        try {
            CafeTable paymentTable = findPaymentTable(newPaymentDto.getTableId());
            List<OrderMenu> orderMenus = paymentTable.getOrderMenus();
            removeOrderMenus(orderMenus);

            PaymentType paymentType = PaymentType.findByName(newPaymentDto.getPaymentType());
            Payment payment = new Payment(paymentTable, paymentType, paymentType.getPaymentAmount(paymentTable));
            paymentRepository.save(payment);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private CafeTable findPaymentTable(final Long tableId) {
        return tableRepository.findById(tableId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("결제할 테이블 Id : %d - 해당 테이블은 결제할 수 없습니다.", tableId)));
    }

    private void removeOrderMenus(final List<OrderMenu> orderMenus) {
        try {
            for (OrderMenu orderMenu : orderMenus) {
                orderRepository.delete(orderMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SaleDto> getPaymentList() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream()
                .map(payment -> SaleDto.builder()
                                .salesId(payment.getId())
                                .tableName(payment.getCafeTable().getTableName())
                                .date(payment.getCreatedAt())
                                .price(payment.getPaymentAmount().getValue())
                                .build())
                .collect(Collectors.toList());
    }

    public PaymentPriceDto getPaymentPrice(final Long tableId) {
        Map<PaymentType, Amount> paymentAmount = getPaymentAmount(findPaymentTable(tableId));
        Amount cashAmount = paymentAmount.get(PaymentType.CASH);
        Amount cardAmount = paymentAmount.get(PaymentType.CARD);

        return new PaymentPriceDto(cashAmount.getValue(), cardAmount.getValue());
    }

    private Map<PaymentType, Amount> getPaymentAmount(final CafeTable cafeTable) {
        Map<PaymentType, Amount> map = new HashMap<>();

        for (PaymentType paymentType : PaymentType.values()) {
            map.put(paymentType, paymentType.getPaymentAmount(cafeTable));
        }
        return map;
    }
}
