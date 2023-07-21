package com.novare.inventoryManager.menus.managerMenu;


import com.novare.inventoryManager.data.order.SalesOrder;
import com.novare.inventoryManager.data.order.SalesOrders;
import com.novare.inventoryManager.data.order.SalesStatistics;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.AddProduct;
import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderController;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderModel;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderView;
import com.novare.inventoryManager.reports.SalesStatisticsReport;
import com.novare.inventoryManager.utils.Menu;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
                case 2 -> {
                    List<SalesOrder> salesOrders = new ArrayList<>();
                    Product apple = new Product(UUID.randomUUID(), "Apple", Measurement.KILOGRAMS, new BigDecimal(10), new BigDecimal(20), new BigDecimal(20));
                    Product orange = new Product(UUID.randomUUID(), "Orange", Measurement.KILOGRAMS, new BigDecimal(30), new BigDecimal(10), new BigDecimal(45));
                    Product lemon = new Product(UUID.randomUUID(), "Lemon", Measurement.KILOGRAMS, new BigDecimal(20), new BigDecimal(10), new BigDecimal(35));

                    salesOrders.add(new SalesOrder(apple, new BigDecimal(2), "2023-07-12", "Customer 1", new BigDecimal(25)));
                    salesOrders.add(new SalesOrder(lemon, new BigDecimal(5), "2023-07-12", "Customer 1", new BigDecimal(15)));
                    salesOrders.add(new SalesOrder(orange, new BigDecimal(1), "2023-07-12", "Customer 1", new BigDecimal(50)));
                    salesOrders.add(new SalesOrder(apple, new BigDecimal(5), "2023-07-12", "Customer 1", new BigDecimal(25)));

                   SalesOrders orders = new SalesOrders(salesOrders);
                    List<SalesStatistics> statisticsData = orders.getMostSoldItemsStatistics(salesOrders);

                     SalesStatisticsReport.generate(statisticsData);
                }
                case 3 -> new AddProduct();
                case 4 -> new PurchaseOrderController(new PurchaseOrderModel(),new PurchaseOrderView()).createPurchaseOrder(); //refactor
                case 5 -> System.out.println("TODO: Update product threshold quantity"); //sprint 2
                case 6 -> System.out.println("TODO: Update product price"); //sprint 2
                case 7 -> {
                    NotificationsImpl notifications = new NotificationsImpl();
                    notifications.printNotifications(notifications.getNotifications());
                }
                case 8 -> System.out.println("TODO: Export transaction list");
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

