package com.novare.inventoryManager.notification;

import com.novare.inventoryManager.utils.Utilities;

import java.util.Date;
import java.util.UUID;

public record Notification(UUID id, Date date_time, String text, UUID productId) {
    @Override
    public String toString() {
        return  id + "," +
                Utilities.convertDateTimeToLong(date_time) + "," +
                text + "," +
                productId;
    }
}
