package com.novare.inventoryManager.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static com.novare.inventoryManager.notification.Utilities.convertDateTimeToLong;
import static com.novare.inventoryManager.notification.Utilities.convertStringToDateTime;

class UtilitiesTest {

    @Test
    void checkCorrectConvertDateTimeToLong() {
        Date date = new Date();
        Long convertedDateTime = convertDateTimeToLong(date);
        System.out.println(date.getTime());
        System.out.println(new Date().getTime());
        Assertions.assertEquals(convertedDateTime,date.getTime());
    }

    @Test
    void checkCorrectConvertStringToDateTime() {
        Date date = new Date();
        String dateTimeInString = String.valueOf(date.getTime());
        Date convertedDateTimeFromString = convertStringToDateTime(dateTimeInString);
        Assertions.assertEquals(convertedDateTimeFromString,date);
    }
}