package com.novare.inventoryManager.data.order;

import com.novare.inventoryManager.data.inventory.Product;

import java.math.BigDecimal;

public class PurchaseOrder extends Order {

    private final String companyName;


    public PurchaseOrder(Product product, BigDecimal orderQuantity, String date, String companyName, BigDecimal price) {
        super(product,orderQuantity,date,price);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }

}
