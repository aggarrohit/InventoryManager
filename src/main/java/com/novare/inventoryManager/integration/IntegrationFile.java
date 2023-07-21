package com.novare.inventoryManager.integration;


import com.novare.inventoryManager.inventory.Measurement;

import java.math.BigDecimal;
import java.util.UUID;

public record IntegrationFile(String orderType,UUID orderId, UUID productId, String productName, Measurement measurement,
                              BigDecimal orderQuantity, String date,String customerName, BigDecimal price) {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(orderType).append(",")
                .append(orderId.toString()).append(",")
                .append(productId.toString()).append(",")
                .append(productName).append(",")
                .append(measurement.getMeasurement()).append(",")
                .append(orderQuantity.toString()).append(",")
                .append(date).append(",")
                .append(customerName).append(",")
                .append(price.toString());
        return stringBuilder.toString();
    }
}
