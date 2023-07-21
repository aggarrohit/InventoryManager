package com.novare.inventoryManager.groupChat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupChatTestData {
    private static final List<Message> chats = new ArrayList<>(List.of(
            new Message(UUID.fromString("a16ebd45-702e-4936-af3e-8e4cc00a8a38"),
                    "John Smith(ADMIN)",UUID.fromString("1e1d7574-98ec-4a85-bb17-f78015ae9373"),
                    Long.parseLong("1689782719900"),"this is my first message")
    ));
    public static List<Message> getChats() {
        return chats;
    }

}
