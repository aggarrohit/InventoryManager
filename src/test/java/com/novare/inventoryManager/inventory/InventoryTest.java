package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

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
    void testCheckAndUpdateOrderQuantities() throws FileNotFoundException, InterruptedException {
        List<Product> products = InventoryFileHelper.getProductsByName(testProductName);
        Product testProduct = products.get(0);
        Product orderProduct = new Product(testProduct.id(),
                                        testProduct.product_name(),
                testProduct.measurement(),
                BigDecimal.valueOf(6),
                testProduct.threshold_qty(),
                testProduct.price());

        List<Product> orderList = new ArrayList<>();
        orderList.add(orderProduct);
        assertTrue(Inventory.checkAndUpdateOrderQuantities(orderList));
        assertFalse(Inventory.checkAndUpdateOrderQuantities(orderList));
    }
}