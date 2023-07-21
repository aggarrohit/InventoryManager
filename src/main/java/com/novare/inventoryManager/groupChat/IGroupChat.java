package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;

import java.io.FileNotFoundException;
import java.util.List;

public interface IGroupChat {
    List<Message> getChatMessages() throws FileNotFoundException;
    void addMessageToGroupChat(String message, Employee employee);
}
