package com.novare.inventoryManager.notification;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotificationsImpl implements INotifications{


    @Override
    public void createNotification(String text, UUID productId) {
    Notification notification = new Notification(UUID.randomUUID(),new Date(),text,productId);
    NotificationFileHelper.addNotification(notification);
    }

    @Override
    public List<Notification> getNotifications() {
        return NotificationFileHelper.getNotifications();
    }

    @Override
    public void printNotifications(List<Notification> notifications) {
        printHeader();
        String tableFormat = "%-7s %-55s %-20s%n";
        System.out.printf(tableFormat, "Sr.No.","Notification", "Date & Time");
        printSeparatorLine();
        for (int i = 0; i < notifications.size(); i++) {
            Notification notification = notifications.get(i);
            System.out.printf(tableFormat,
                    i+1,
                    notification.text(),
                    Utilities.formatDate(notification.date_time())
            );
        }
        printSeparatorLine();
    }

    private static void printSeparatorLine(){
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private static void printHeader(){
        printSeparatorLine();
        System.out.println("*************************************** Notifications ***************************************");
        printSeparatorLine();
    }
}
