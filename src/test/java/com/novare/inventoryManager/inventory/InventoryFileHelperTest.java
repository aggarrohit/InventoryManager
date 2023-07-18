package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFileHelperTest {

    @Test
    void checkIfUpdateProductQuantity() throws IOException {
        List<Product> products = InventoryFileHelper.getProductsByName("noti test");
        Product notiTestProduct = products.get(0);
        InventoryFileHelper.updateQuantity(notiTestProduct.id(), BigDecimal.valueOf(8));

        List<Product> productsUpdated = InventoryFileHelper.getProductsByName("noti test");
        Product notificationTestProductUpdated = productsUpdated.get(0);

        assertEquals(notificationTestProductUpdated.quantity(),BigDecimal.valueOf(8));
    }

    @Test
    void checkIfUpdateProductThresholdQuantity() throws IOException {
        List<Product> products = InventoryFileHelper.getProductsByName("noti test");
        Product notiTestProduct = products.get(0);
        InventoryFileHelper.updateThresholdQuantity(notiTestProduct.id(), BigDecimal.valueOf(5));

        List<Product> productsUpdated = InventoryFileHelper.getProductsByName("noti test");
        Product notificationTestProductUpdated = productsUpdated.get(0);

        assertEquals(notificationTestProductUpdated.threshold_qty(),BigDecimal.valueOf(5));
    }

}