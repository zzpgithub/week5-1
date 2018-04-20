package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {

    public BigDecimal calculate(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts, BigDecimal tax) {

        BigDecimal subTotal = new BigDecimal(0);
        subTotal = subtractDiscounts(totalUpLineItems(subTotal, orderLineItemList),discounts);
        BigDecimal tax1 = calculateTax(subTotal, tax);
        return calculateGrandTotal(subTotal, tax1);
    }

    public BigDecimal totalUpLineItems(BigDecimal subtotal, List<OrderLineItem> orderLineItemList) {
        for (OrderLineItem lineItem : orderLineItemList) {
            subtotal = subtotal.add(lineItem.getPrice());
        }
        return subtotal;
    }

    public BigDecimal subtractDiscounts(BigDecimal subtotal, List<BigDecimal> discounts) {
        for (BigDecimal discount : discounts) {
            subtotal = subtotal.subtract(discount);
        }
        return subtotal;
    }

    public BigDecimal calculateTax(BigDecimal subtotal, BigDecimal tax) {
        return subtotal.multiply(tax);
    }

    public BigDecimal calculateGrandTotal(BigDecimal subtotal, BigDecimal tax) {
        return subtotal.add(tax);
    }
}
