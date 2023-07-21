package com.novare.inventoryManager.product.updateProduct;

import java.math.BigDecimal;
import java.util.UUID;

public interface IUpdateProduct {
    void updatePrice(UUID productId, BigDecimal price);
    void updateThresholdQuantity(UUID productId,BigDecimal thresholdQty);
}
