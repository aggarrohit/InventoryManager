package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.utils.Utilities;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GroupChatModel implements IGroupChat{

    public String getTextInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public void addMessageToGroupChat(String message, Employee employee) {
        ChatFileHelper.addMessage(createMessage(message, employee));
    }
    @Override
    public List<Message> getChatMessages() throws FileNotFoundException {
        return ChatFileHelper.getMessages();
    }

    private Message createMessage(String messageText, Employee employee) {
        return new Message(  UUID.randomUUID(),
                employee.getFullName()+"("+employee.getRole()+")",
                employee.getId(),
                Utilities.convertDateTimeToLong(new Date()),
                messageText
        );
    }


}
