package com.novare.inventoryManager.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesOrder extends Order {
    private final String customerName;

    public SalesOrder(UUID orderId, Product product, BigDecimal orderQuantity, String date, String customerName, BigDecimal price) {
        super(orderId,product, orderQuantity, date, price);
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
