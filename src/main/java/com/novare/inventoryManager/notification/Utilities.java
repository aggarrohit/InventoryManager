package com.novare.inventoryManager.notification;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static long convertDateTimeToLong(Date date_time){
        return date_time.getTime();
    }

    public static Date convertLongToDateTime(Long date_time){
        return new Date(date_time);
    }

    public static Date convertStringToDateTime(String date_time){
        return new Date(Long.parseLong(date_time));
    }

    public static String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}
