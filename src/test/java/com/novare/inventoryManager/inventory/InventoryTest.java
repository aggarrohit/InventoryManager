package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void testCheckAndUpdateOrderQuantities() throws FileNotFoundException, InterruptedException {
        List<Product> products = InventoryFileHelper.getProductsByName("noti test");
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