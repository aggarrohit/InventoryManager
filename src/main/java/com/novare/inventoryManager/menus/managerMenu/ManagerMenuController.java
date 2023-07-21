package com.novare.inventoryManager.menus.managerMenu;


import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.groupChat.GroupChat;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.addProduct.AddProduct;
import com.novare.inventoryManager.product.updateProduct.UpdateProduct;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderController;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderModel;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderView;
import com.novare.inventoryManager.utils.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.novare.inventoryManager.inventory.Constants.PRODUCT_PRICE;
import static com.novare.inventoryManager.inventory.Constants.PRODUCT_THRESHOLD_QUANTITY;

class ManagerMenuController {
    private final ManagerMenuModel model;
    private final ManagerMenuView view;
    private final Scanner scanner;
    private final Employee employee;
    ManagerMenuController(ManagerMenuModel model, ManagerMenuView view,Employee employee) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.employee = employee;
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
                case 4 -> new PurchaseOrderController(new PurchaseOrderModel(),new PurchaseOrderView()).createPurchaseOrder(); //refactor
                case 5 -> new UpdateProduct(PRODUCT_THRESHOLD_QUANTITY); //sprint 2
                case 6 -> new UpdateProduct(PRODUCT_PRICE); //sprint 2
                case 7 -> {
                    NotificationsImpl notifications = new NotificationsImpl();
                    notifications.printNotifications(notifications.getNotifications());
                }
                case 8 -> System.out.println("TODO: Export transaction list");
                case 9 -> new GroupChat(employee); // sprint 2

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

