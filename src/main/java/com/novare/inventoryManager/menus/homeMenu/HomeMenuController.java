package com.novare.inventoryManager.menus.homeMenu;

import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.menus.loginMenu.LoginMenu;
import com.novare.inventoryManager.utils.Menu;

import java.util.Scanner;

class HomeMenuController {
    private final HomeMenuModel model;
    private final HomeMenuView view;
    private final Scanner scanner;
    private EmployeeRole role;

    HomeMenuController(HomeMenuModel model, HomeMenuView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    void requestUserInput() {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) {
            Menu.printGoodbyeMessage();
            System.exit(0);
        }

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
            case 1 -> role = EmployeeRole.ADMIN;
            case 2 -> role = EmployeeRole.MANAGER;
            case 3 -> role = EmployeeRole.CASHIER;
            default -> throw new IndexOutOfBoundsException();
        }
        new LoginMenu(role);
    }
}
