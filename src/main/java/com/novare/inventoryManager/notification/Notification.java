package com.novare.inventoryManager.notification;

import com.novare.inventoryManager.utils.Utilities;

import java.util.Date;
import java.util.UUID;

public record Notification(UUID id, Date date_time, String text, UUID productId) {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(",")
                .append(Utilities.convertDateTimeToLong(date_time)).append(",")
                .append(text).append(",")
                .append(productId);
        return stringBuilder.toString();
    }
}
