package com.novare.inventoryManager.product;

import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryTestData {
    private static final List<Product> products = new ArrayList<>(List.of(
            new Product(UUID.randomUUID(),"iPhone 12 Pro", Measurement.PIECES, BigDecimal.valueOf(15), BigDecimal.valueOf(5), BigDecimal.valueOf(10990.99)),
            new Product(UUID.randomUUID(),"Samsung Galaxy S21 Ultra", Measurement.PIECES, BigDecimal.valueOf(10), BigDecimal.valueOf(3), BigDecimal.valueOf(12990.99)),
            new Product(UUID.randomUUID(),"Sony Bravia 55-inch 4K TV", Measurement.PIECES, BigDecimal.valueOf(5), BigDecimal.valueOf(2), BigDecimal.valueOf(14990.99)),
            new Product(UUID.randomUUID(),"Samsung 970 EVO Plus 1TB SSD", Measurement.PIECES, BigDecimal.valueOf(20), BigDecimal.valueOf(10), BigDecimal.valueOf(1790.99)),
            new Product(UUID.randomUUID(),"WD Elements 4TB External Hard Drive", Measurement.PIECES, BigDecimal.valueOf(8), BigDecimal.valueOf(4), BigDecimal.valueOf(1390.99)),
            new Product(UUID.randomUUID(),"Apple AirPods Pro", Measurement.PIECES, BigDecimal.valueOf(25), BigDecimal.valueOf(5), BigDecimal.valueOf(2490.99)),
            new Product(UUID.randomUUID(),"Logitech MX Master 3 Mouse", Measurement.PIECES, BigDecimal.valueOf(12), BigDecimal.valueOf(2), BigDecimal.valueOf(990.99))));
    public static List<Product> getProducts() {
        return products;
    }

}
