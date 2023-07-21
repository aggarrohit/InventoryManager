package com.novare.inventoryManager.menus.cashierMenu;

import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.saleOrder.SalesOrderMain;
import com.novare.inventoryManager.utils.Menu;

import java.io.FileNotFoundException;
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

                case 1 -> Inventory.listInventory();
                case 2 -> new SalesOrderMain("createSalesOrder");
                case 3 -> System.out.println("TODO: Open the group chat"); // sprint 2

                case 4 -> Menu.redirectToHomeMenu();
                default -> Menu.printInvalidOption();
            }
            runMenu();
        }
        catch(IndexOutOfBoundsException exception) {
            Menu.printInvalidOption();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void runMenu(){
        Menu.displayMenu(model.menuOptions);
        requestUserInput();
    }
}
