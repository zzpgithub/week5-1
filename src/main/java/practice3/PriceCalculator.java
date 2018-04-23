package practice3;

import java.math.BigDecimal;

public class PriceCalculator {

    public static final int DEFAULT_VAL = 0;

    private BigDecimal subTotal;
    private Order order;
    public PriceCalculator(Order order) {
        this.order = order;
        subTotal  = new BigDecimal(DEFAULT_VAL);
    }

    public BigDecimal calculate() {
        subTotal = calculateSubTotal();
        BigDecimal tax = subTotal.multiply(order.getTax());
        return subTotal.add(tax);
    }

    public BigDecimal calculateSubTotal() {
        for (OrderLineItem lineItem : order.getOrderLineItemList()) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        for (BigDecimal discount : order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }
}
