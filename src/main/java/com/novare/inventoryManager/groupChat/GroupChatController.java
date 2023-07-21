package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;

import java.io.FileNotFoundException;

public class GroupChatController {

    final GroupChatView groupChatView;
    final GroupChatModel groupChatModel;
    final Employee employee;

    public GroupChatController(GroupChatModel groupChatModel, GroupChatView groupChatView,Employee employee)
                                throws FileNotFoundException,IndexOutOfBoundsException {
        this.groupChatModel=groupChatModel;
        this.groupChatView=groupChatView;
        this.employee=employee;

        refreshChat();

    }

    private void refreshChat() throws FileNotFoundException {
        showChat();
        requestMessage();
    }

    private void showChat() throws FileNotFoundException {
        groupChatView.showChatMessages(groupChatModel.getChatMessages());
    }


    private void requestMessage() throws FileNotFoundException {
        groupChatView.requestMessage();
        try {
            checkMessage();
        }catch (IllegalArgumentException e){
            groupChatView.printExceptionMessage(e);
            requestMessage();
        }
    }

    private void checkMessage() throws IllegalArgumentException, FileNotFoundException {
        String message = groupChatModel.getTextInput();
        switch (message.trim()){
            case ""-> throw new IllegalArgumentException("Nothing typed..");
            case "r"->refreshChat();
            case "0"->groupChatView.showEmployeeMenu(employee);
            default -> {
                            groupChatModel.addMessageToGroupChat(message,employee);
                            groupChatView.showChatSent();
                            requestMessage();
                        }
        }
    }



}
