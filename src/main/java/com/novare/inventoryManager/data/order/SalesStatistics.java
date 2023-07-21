package com.novare.inventoryManager.data.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;

public record SalesStatistics(Product product, BigDecimal totalQuantitySold, BigDecimal averagePrice) {
}
