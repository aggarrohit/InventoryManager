package com.novare.inventoryManager.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final Product product;
    private final BigDecimal orderQuantity;
    private final String date;
    private final BigDecimal price;

    public Order(UUID orderId,Product product, BigDecimal orderQuantity, String date, BigDecimal price) {
        this.orderId=orderId;
        this.product = product;
        this.orderQuantity = orderQuantity;
        this.date = date;
        this.price = price;
    }

    public UUID getOrderId() {
        return orderId;
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
