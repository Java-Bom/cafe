package domain.payment;

import domain.menu.OrderMenu;
import domain.vo.Amount;

import java.util.List;
import java.util.stream.Collectors;

public class Payment {

    private DiscountCondition paymentTypeDiscountCondition = new PaymentTypeDiscountCondition();

    private DiscountCondition quantityDiscountCondition = new QuantityDiscountCondition();

    public Amount getPaymentAmount(final List<OrderMenu> orderMenus, final PaymentType paymentType) {
        List<Amount> quantityDiscountAmounts = fitQuantityDiscountAmounts(orderMenus);

        return fitPaymentTypeDiscount(quantityDiscountAmounts, paymentType);
    }

    private Amount fitPaymentTypeDiscount(final List<Amount> quantityDiscountAmounts, final PaymentType paymentType) {
        Amount totalAmount = Amount.valueOf(0);

        for (Amount quantityDiscountAmount : quantityDiscountAmounts) {
            totalAmount = totalAmount.add(quantityDiscountAmount);
        }

        if (paymentType.equals(PaymentType.CASH)) {
            totalAmount = paymentTypeDiscountCondition.getDiscountAmount(totalAmount);
        }

        return totalAmount;
    }

    private List<Amount> fitQuantityDiscountAmounts(final List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(orderMenu -> orderMenu.calculateDiscountAmount(quantityDiscountCondition))
                .collect(Collectors.toList());
    }
}
