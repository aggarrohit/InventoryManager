package com.novare.inventoryManager.notification;

import com.novare.inventoryManager.utils.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.novare.inventoryManager.inventory.Constants.PATH_TO_ASSETS;

public class NotificationFileHelper {

    private static final String PATH_TO_NOTIFICATION_FILE = PATH_TO_ASSETS + "/notification.txt";

    private NotificationFileHelper() {
    }

    public static List<Notification> getNotifications() {
        Scanner sc;
        try {
            sc = new Scanner(new File(PATH_TO_NOTIFICATION_FILE));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Notification> notifications = new ArrayList<>();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if(!nextLine.isEmpty()) {
                String[] notificationFields = nextLine.split(",");

                    Notification notification = new Notification(UUID.fromString(notificationFields[0]),
                            Utilities.convertStringToDateTime(notificationFields[1]),
                            notificationFields[2],
                            UUID.fromString(notificationFields[3])

                    );
                    notifications.add(notification);


            }
        }
        sc.close();
        return notifications;
    }

    public static void addNotification(Notification notification) throws IllegalArgumentException {
        try {


            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_NOTIFICATION_FILE, true));
            writer.write(notification+System.lineSeparator());

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
