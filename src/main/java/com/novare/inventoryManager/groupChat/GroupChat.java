package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;

import java.io.FileNotFoundException;

public class GroupChat {
    public GroupChat(Employee employee) {
        GroupChatModel groupChatModel = new GroupChatModel();
        GroupChatView groupChatView = new GroupChatView();
        try {
            new GroupChatController(groupChatModel,groupChatView,employee);
        } catch (FileNotFoundException e) {
            groupChatView.showFileError();
            throw new RuntimeException(e);
        }
    }
}
