package com.novare.inventoryManager.notification;

import com.novare.inventoryManager.utils.Table;
import com.novare.inventoryManager.utils.Utilities;

import java.util.ArrayList;
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

        List<Integer> columnWidths = List.of(7, 55, 20);
        List<String> headers = List.of("Sr.No.","Notification", "Date & Time");
        List<List<String>> body = parseNotification(notifications);
        Table table = new Table(columnWidths, headers, body,"Notifications");

        table.showData();

    }

    private List<List<String>> parseNotification(List<Notification> notifications) {
        List<List<String>> result = new ArrayList<>();

        int sr_no = 0;
        for (Notification notification: notifications) {
            sr_no++;
            String srNo = String.valueOf(sr_no);
            String text = notification.text();
            String date_time = Utilities.formatDate(notification.date_time());
            List<String> data = List.of(srNo,text,date_time);

            result.add(data);
        }

        return result;
    }

}
