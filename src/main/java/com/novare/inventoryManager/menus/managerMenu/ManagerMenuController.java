package com.novare.inventoryManager.menus.managerMenu;

import com.novare.inventoryManager.utils.Menu;

import java.util.Scanner;

class ManagerMenuController {
    private final ManagerMenuModel model;
    private final ManagerMenuView view;
    private final Scanner scanner;
    ManagerMenuController(ManagerMenuModel model, ManagerMenuView view) {
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
            switch (selectedOption) {
                case 1 -> System.out.println("TODO: View the inventory products/entries");
                case 2 -> System.out.println("TODO: Generate report");
                case 3 -> System.out.println("TODO:  Add new product");
                case 4 -> System.out.println("TODO: Add orders to the orders list");
                case 5 -> System.out.println("TODO: Add receipts");
                case 6 -> System.out.println("TODO: Open the group chat");

                case 7 -> Menu.redirectToHomeMenu();
                default -> throw new IndexOutOfBoundsException();
            }
        }

}
