package com.novare.inventoryManager.menus.managerMenu;


import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.groupChat.GroupChat;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.order.SalesOrderInventory;
import com.novare.inventoryManager.order.SalesStatistics;
import com.novare.inventoryManager.product.addProduct.AddProduct;
import com.novare.inventoryManager.reports.SalesStatisticsReport;;
import com.novare.inventoryManager.product.updateProduct.UpdateProduct;
import com.novare.inventoryManager.integration.IntegrationImpl;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderMain;
import com.novare.inventoryManager.saleOrder.SalesOrderMain;
import com.novare.inventoryManager.utils.Menu;

import java.io.FileNotFoundException;
import java.util.List;
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
            case 2 -> new SalesOrderMain("showSalesOrders");
            case 3 -> new PurchaseOrderMain("showPurchaseOrders");
            case 4 -> new AddProduct();
            case 5 -> new PurchaseOrderMain("createPurchaseOrder");
            case 6 -> new UpdateProduct(PRODUCT_THRESHOLD_QUANTITY);
            case 7 -> new UpdateProduct(PRODUCT_PRICE);
            case 8 -> {
                NotificationsImpl notifications = new NotificationsImpl();
                notifications.printNotifications(notifications.getNotifications());
            }
            case 9 -> {
                SalesOrderInventory salesOrderInventory = new SalesOrderInventory();
                SalesStatisticsReport.generate(salesOrderInventory.getMostSoldItemsStatistics());
            }
            case 10 -> new IntegrationImpl().createIntegrateFile();
            case 11 -> new GroupChat(employee);
            case 12 -> Menu.redirectToHomeMenu();
            default -> throw new IndexOutOfBoundsException();
        }
        runMenu();
    }

    void runMenu(){
        Menu.displayMenu(model.menuOptions);
        requestUserInput();
    }
}

