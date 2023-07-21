package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.order.SalesOrder;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;

public class SalesOrderController {
    private final SalesOrderModel model;
    private final SalesOrderView view;


    public SalesOrderController(SalesOrderModel model, SalesOrderView view) {
        this.model = model;
        this.view = view;
    }

    public synchronized void createSalesOrder() throws FileNotFoundException {
        List<SalesOrder> salesOrder = Collections.synchronizedList(new ArrayList<>());
        List<Product> inventory = model.getInventoryProducts();
        view.displayInventory(inventory);

        boolean wantsToAddMoreItems = true;
        LocalDate date = LocalDate.now();
        UUID orderId=UUID.randomUUID();
        while (wantsToAddMoreItems) {
            inventory=model.getInventoryProducts();
            int itemNumber;
            Product selectedProduct;
            BigDecimal quantity;
            BigDecimal availableQuantity;
            String customerName;

            // Get valid item number
            while (true) {
                itemNumber = view.getIntNumericUserInput("""
                                                
                        Sales order details
                        Enter the item number\s""");
                if (itemNumber <= 0 || itemNumber > inventory.size()) {
                    view.displayErrorMessage("Enter a valid number between 1 and " + inventory.size());
                } else {
                    selectedProduct = inventory.get(itemNumber - 1);
                    break;
                }
            }
            availableQuantity = selectedProduct.quantity();
            view.displayInventoryItem(selectedProduct);

            // Get valid quantity
            while (true) {
                quantity = view.getBigDecimalNumericUserInput("Enter the quantity");
                if (quantity == null || quantity.doubleValue() < 0) {
                    view.displayErrorMessage("Enter a valid quantity.");
                } else if (quantity.doubleValue() > availableQuantity.doubleValue()) {
                    view.displayErrorMessage("Insufficient quantity. Available: " + availableQuantity);
                } else {
                    break;
                }
            }
            // Get valid customer name
            while (true) {
                customerName = view.getInput("Enter the customer name");
                if (customerName.isEmpty()) {
                    view.displayErrorMessage("Enter a valid customer name.");
                } else {
                    break;
                }
            }
            salesOrder.add(new SalesOrder(orderId,selectedProduct, quantity, date.toString(), customerName, selectedProduct.price()));
            model.updateProductQuantityById(selectedProduct.id(), selectedProduct.quantity().subtract(quantity));
            wantsToAddMoreItems = view.getYesNoUserInput("Do you want to add more items? (Y/N)");
        }
        if (!salesOrder.isEmpty()) {
            model.addSalesOrderToOrderInventory(salesOrder);
            view.displaySalesOrder(salesOrder);
            view.displayInputMessage("Sales order submitted Successfully. You return to the previous menu");
        }
    }
    public void printSalesOrdersInInventory() {
        List<SalesOrder> saleOrders = model.getSalesOrderList();
        if (saleOrders.isEmpty()) {
            view.displayInputMessage("\nSales order inventory is empty.");
        } else {
            view.displayInputMessage("\nSales orders in the inventory:");
            view.displayOrderInventory(saleOrders);
        }
    }
}
