package com.novare.inventoryManager.data.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;

public class SalesOrder extends Order {
    private final String customerName;

    public SalesOrder(Product product, BigDecimal orderQuantity, String date, String customerName, BigDecimal price) {
        super(product, orderQuantity, date, price);
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
