package com.novare.inventoryManager.groupChat;

import java.util.UUID;

public record Message(UUID id, String sender_name, UUID user_id, Long date_time, String message) {

    @Override
    public String toString() {
        return  id + "," +
                sender_name + "," +
                user_id + "," +
                date_time + "," +
                message;
    }
}
