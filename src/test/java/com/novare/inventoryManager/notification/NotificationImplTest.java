package com.novare.inventoryManager.notification;

import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationImplTest {

    private static final String testProductName = "test product notification";
    @BeforeEach
    @Test
    void addTestProductIfNotPresent_OrRefillQuantity() throws FileNotFoundException {
        if(!Inventory.doesProductExist(testProductName)) {
            Product product = new Product(UUID.randomUUID(), testProductName, Measurement.KILOGRAMS, BigDecimal.TEN,
                    BigDecimal.valueOf(5), BigDecimal.valueOf(25));
            Inventory.addProductToInventory(product);
        }else{
            Product product = InventoryFileHelper.getProductsByName(testProductName).get(0);
            InventoryFileHelper.updateQuantity(product.id(),BigDecimal.TEN);
        }
    }

    @Test
    void testIfNotificationCreated_WhenAvailableQuantityIs_Less_ThanThresholdQuantity() throws FileNotFoundException {
        NotificationsImpl notifications = new NotificationsImpl();

        int numberOfNotificationsBefore = notifications.getNotifications().size();

        Product product = InventoryFileHelper.getProductsByName(testProductName).get(0);

        BigDecimal productThresholdQty = product.threshold_qty();

        InventoryFileHelper.updateQuantity(product.id(),productThresholdQty.subtract(BigDecimal.ONE));

        int numberOfNotificationsAfter = notifications.getNotifications().size();

        assertEquals(numberOfNotificationsBefore+1,numberOfNotificationsAfter);
    }

    @Test
    void testIfNotificationCreated_WhenAvailableQuantityIs_Greater_ThanThresholdQuantity() throws FileNotFoundException {
        NotificationsImpl notifications = new NotificationsImpl();

        int numberOfNotificationsBefore = notifications.getNotifications().size();

        Product product = InventoryFileHelper.getProductsByName(testProductName).get(0);

        BigDecimal productThresholdQty = product.threshold_qty();

        InventoryFileHelper.updateQuantity(product.id(),productThresholdQty.add(BigDecimal.ONE));

        int numberOfNotificationsAfter = notifications.getNotifications().size();

        assertEquals(numberOfNotificationsBefore,numberOfNotificationsAfter);
    }
}