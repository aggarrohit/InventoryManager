package com.novare.inventoryManager.menus.cashierMenu;

import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.menus.cashierMenu.CashierMenuModel;
import com.novare.inventoryManager.menus.cashierMenu.CashierMenuView;
import com.novare.inventoryManager.utils.Menu;

import java.util.Scanner;

class CashierMenuController {
    private final CashierMenuModel model;
    private final CashierMenuView view;
    private final Scanner scanner;

    CashierMenuController(CashierMenuModel model, CashierMenuView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    void requestUserInput() {
        String input = scanner.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);
            handleOption(selectedOption);
        }
        catch (NumberFormatException | IndexOutOfBoundsException exception) {
            Menu.printInvalidOption();

            Menu.printRequest();
            requestUserInput();
        }
    }

    void handleOption(int selectedOption) throws IndexOutOfBoundsException {
        try {
            switch (selectedOption) {

                case 1 -> System.out.println("TODO: View the inventory products");
                case 2 -> System.out.println("TODO: Add orders to the orders list");
                case 3 -> System.out.println("TODO: Sell an item");
                case 4 -> System.out.println("TODO: Open the group chat");

                case 5 -> Menu.redirectToHomeMenu();
                default -> Menu.printInvalidOption();
            }
        }
        catch(IndexOutOfBoundsException exception) {
            Menu.printInvalidOption();
        }
    }

}
