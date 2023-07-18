package com.novare.inventoryManager;

import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.AddProduct;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("7.5");
        BigDecimal bigDecimal2 = new BigDecimal("2");
        System.out.println(bigDecimal2.compareTo(bigDecimal1));
//        new AddProduct();
//        NotificationsImpl notifications = new NotificationsImpl();
//        notifications.printNotifications(notifications.getNotifications());
    }
}