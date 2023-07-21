package com.novare.inventoryManager.groupChat;

import java.io.*;
import java.util.*;

import static com.novare.inventoryManager.inventory.Constants.PATH_TO_ASSETS;

public class ChatFileHelper {

    private static final String PATH_TO_CHAT_FILE = PATH_TO_ASSETS + "/chat.txt";

    private ChatFileHelper() {
    }


    public static List<Message> getMessages() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(PATH_TO_CHAT_FILE));
        List<Message> messages = new ArrayList<>();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if(!nextLine.isEmpty()) {
                Message message = convertStringToMessage(nextLine);
                messages.add(message);
            }
        }
        sc.close();
        return messages;
    }

    public static void addMessage(Message message) throws IllegalArgumentException {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_CHAT_FILE, true));
            writer.write(convertMessageToStringWithEncodedText(message)+System.lineSeparator());

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String convertMessageToStringWithEncodedText(Message message){
        String textMessage = message.message();
        String encodedTextString = Base64.getEncoder().encodeToString(textMessage.getBytes());
        Message encodedTextMessage = new Message(message.id(),message.sender_name(),message.user_id(),message.date_time(),
                                                encodedTextString);
        return encodedTextMessage.toString();
    }

    private static Message convertStringToMessage(String messageString) {
        String[] messageFields = messageString.split(",");
        return new Message(  UUID.fromString(messageFields[0]),
                messageFields[1],
                UUID.fromString(messageFields[2]),
                Long.parseLong(messageFields[3]),
                new String(Base64.getDecoder().decode(messageFields[4]))
        );
    }

}
