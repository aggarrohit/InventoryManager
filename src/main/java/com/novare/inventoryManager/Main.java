package com.novare.inventoryManager;

import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.AddProduct;

public class Main {
    public static void main(String[] args) {
        new AddProduct();
//        NotificationsImpl notifications = new NotificationsImpl();
//        notifications.printNotifications(notifications.getNotifications());
    }
}