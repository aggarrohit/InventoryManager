package com.novare.inventoryManager.notification;

import java.util.List;
import java.util.UUID;

public interface INotifications {
    void createNotification(String text, UUID productId);

    List<Notification> getNotifications();

    void printNotifications(List<Notification> notifications);
}
