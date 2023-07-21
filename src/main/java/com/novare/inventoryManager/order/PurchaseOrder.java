package com.novare.inventoryManager.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class PurchaseOrder extends Order {
    private final String companyName;

    public PurchaseOrder(UUID orderId,Product product, BigDecimal orderQuantity, String date, String companyName, BigDecimal price) {
        super(orderId,product,orderQuantity,date,price);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }

}
