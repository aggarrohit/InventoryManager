package com.novare.inventoryManager.product;

import com.novare.inventoryManager.inventory.Measurement;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String product_name, Measurement measurement, BigDecimal quantity, BigDecimal threshold_qty, BigDecimal price) {

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(",")
                .append(product_name).append(",")
                .append(measurement.getMeasurement()).append(",")
                .append(quantity).append(",")
                .append(threshold_qty).append(",")
                .append(price);
        return stringBuilder.toString();
    }
}
