package com.novare.inventoryManager.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;

public record SalesStatistics(Product product, BigDecimal totalQuantitySold, BigDecimal averagePrice) {
}
