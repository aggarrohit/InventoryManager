package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFileHelperTest {

    private static final String testProductName = "test product inv";
    @BeforeAll
    @Test
    static void addTestProductIfNotPresent(){
        if(Inventory.doesProductExist(testProductName)) return;
        Product product = new Product(UUID.randomUUID(),testProductName,Measurement.KILOGRAMS,BigDecimal.TEN,
                                        BigDecimal.valueOf(5),BigDecimal.valueOf(25));
        Inventory.addProductToInventory(product);
    }

    @Test
    void checkIfUpdateProductQuantity() throws IOException {
        List<Product> products = InventoryFileHelper.getProductsByName(testProductName);
        if(products.size()==0) throw new IndexOutOfBoundsException("product not found");

        Product testProduct = products.get(0);
        InventoryFileHelper.updateQuantity(testProduct.id(), BigDecimal.valueOf(8));

        List<Product> productsUpdated = InventoryFileHelper.getProductsByName(testProductName);
        Product testProductUpdated = productsUpdated.get(0);

        assertEquals(testProductUpdated.quantity(),BigDecimal.valueOf(8));
    }

    @Test
    void checkIfUpdateProductThresholdQuantity() throws IOException {
        List<Product> products = InventoryFileHelper.getProductsByName(testProductName);
        if(products.size()==0) throw new IndexOutOfBoundsException("product not found");

        Product testProduct = products.get(0);
        InventoryFileHelper.updateThresholdQuantity(testProduct.id(), BigDecimal.valueOf(5));

        List<Product> productsUpdated = InventoryFileHelper.getProductsByName(testProductName);
        Product notificationTestProductUpdated = productsUpdated.get(0);

        assertEquals(notificationTestProductUpdated.threshold_qty(),BigDecimal.valueOf(5));
    }

}