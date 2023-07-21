package com.novare.inventoryManager.menus.managerMenu;


import com.novare.inventoryManager.integration.IntegrationImpl;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.AddProduct;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderMain;
import com.novare.inventoryManager.utils.Menu;

import java.io.FileNotFoundException;
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void handleOption(int selectedOption) throws IndexOutOfBoundsException, FileNotFoundException {
            switch (selectedOption) {
                case 1 -> Inventory.listInventory();
                case 2 -> System.out.println("TODO: Generate report"); //sprint 2
                case 3 -> new AddProduct();
                case 4 -> new PurchaseOrderMain("showPurchaseOrders"); //refactor
                case 5 -> System.out.println("TODO: Update product threshold quantity"); //sprint 2
                case 6 -> System.out.println("TODO: Update product price"); //sprint 2
                case 7 -> {
                    NotificationsImpl notifications = new NotificationsImpl();
                    notifications.printNotifications(notifications.getNotifications());
                }
                case 8 -> new IntegrationImpl().createIntegrateFile();
                case 9 -> System.out.println("TODO: Open the group chat"); // sprint 2

                case 10 -> Menu.redirectToHomeMenu();
                default -> throw new IndexOutOfBoundsException();
            }
            runMenu();
        }

    void runMenu(){
        Menu.displayMenu(model.menuOptions);
        requestUserInput();
    }
}

