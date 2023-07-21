package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.menus.adminMenu.AdminMenu;
import com.novare.inventoryManager.menus.cashierMenu.CashierMenu;
import com.novare.inventoryManager.menus.homeMenu.HomeMenu;
import com.novare.inventoryManager.menus.managerMenu.ManagerMenu;
import com.novare.inventoryManager.utils.Utilities;

import java.util.List;

public class GroupChatView {
    public void printExceptionMessage(Exception exception){
        System.out.println(exception.getMessage());
    }

    public void requestMessage() {
        System.out.print("""
                Enter 0 to leave chat or
                Enter r to refresh chat or
                type message:\s""");
    }

    public void showChatSent() {
        System.out.println("Message sent to group chat!");
    }

    public void showFileError() {
        System.out.println("Chat file not found!");
    }

    public void showChatMessages(List<Message> messages){
        for (Message message:messages){
            String chatRow =
                    message.sender_name() +
                    ": " +
                    message.message() +
                    " @" +
                    Utilities.formatDate(Utilities.convertStringToDateTime(message.date_time().toString()));
            System.out.println(chatRow);
        }
    }

    public void showEmployeeMenu(Employee employee){
        switch (employee.getRole()){
            case ADMIN->new AdminMenu(employee);
            case MANAGER->new ManagerMenu(employee);
            case CASHIER->new CashierMenu(employee);
            default -> new HomeMenu();
        }
    }
}
