package com.novare.inventoryManager.data.order;

import com.novare.inventoryManager.data.inventory.Product;

import java.math.BigDecimal;

public class Order {
    private final Product product;
    private final BigDecimal orderQuantity;
    private final String date;
    private final BigDecimal price;

    public Order(Product product, BigDecimal orderQuantity, String date, BigDecimal price) {
        this.product = product;
        this.orderQuantity = orderQuantity;
        this.date = date;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
