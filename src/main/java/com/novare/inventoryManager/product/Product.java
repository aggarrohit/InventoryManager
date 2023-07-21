package com.novare.inventoryManager.product;

import com.novare.inventoryManager.inventory.Measurement;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String product_name, Measurement measurement, BigDecimal quantity, BigDecimal threshold_qty, BigDecimal price) {

    @Override
    public String toString() {
        return  id + "," +
                product_name + "," +
                measurement.getMeasurement() + "," +
                quantity + "," +
                threshold_qty + "," +
                price;
    }
}
