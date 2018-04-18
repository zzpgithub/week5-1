package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    public static final double TAX_VAL = 0.1;
    public static final int NEW_SCALE = 2;

    private BigDecimal tax;

    public Receipt() {
        tax = new BigDecimal(TAX_VAL);
        tax = tax.setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items).subtract(calculateDiscountTotal(products, items));
        BigDecimal taxTotal = calculateTaxTotal(subTotal);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal calculateTaxTotal(BigDecimal subtotal) {
        return subtotal.multiply(tax);
    }
    private BigDecimal calculateDiscountTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal reducedPrice = new BigDecimal(0);
        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);

            reducedPrice=reducedPrice.add(product.getPrice()
                    .multiply(product.getDiscountRate())
                    .multiply(new BigDecimal(curItem.getCount())));

        }
        return reducedPrice;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
