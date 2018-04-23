package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    public static final double TAX_VAL = 0.1;

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public List<BigDecimal> getDiscounts() {
        return discounts;
    }

    public BigDecimal getTax() {
        return tax;
    }

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(TAX_VAL);
    }

    public BigDecimal calculate() {
        return new PriceCalculator(this).calculate();
    }
}
