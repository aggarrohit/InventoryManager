package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.data.inventory.Product;
import com.novare.inventoryManager.data.order.SalesOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class SalesOrderController {
    private final SalesOrderModel model;
    private final SalesOrderView view;

    public SalesOrderController(SalesOrderModel model, SalesOrderView view) {
        this.model = model;
        this.view = view;
    }

    public void createSalesOrder() {
        List<SalesOrder> salesOrder = new ArrayList<>();
        List<Product> inventory = model.getInventoryProducts();
        view.displayInventory(inventory);

        String customerName = view.getInput("Enter the customer name: ");
        //SalesOrder order = new SalesOrder(customerName);

        boolean wantsToAddMoreItems = true;
        LocalDate date = LocalDate.now();
        while (wantsToAddMoreItems) {
            int itemNumber = view.getIntNumericUserInput("""

                    Sales order details
                    Enter the item number:\s""");
            if (itemNumber <= 0 || itemNumber > inventory.size()) {
                view.displayErrorMessage("Enter a valid number between 1 and " + inventory.size());
                continue;
            }
            Product selectedProduct = inventory.get(itemNumber - 1);
            BigDecimal availableQuantity = selectedProduct.getQuantity();
            view.displayInventoryItem(selectedProduct);

            BigDecimal quantity = view.getBigDecimalNumericUserInput("Enter the quantity: ");
            if (quantity.doubleValue() <= 0) {
                view.displayErrorMessage("Enter a valid quantity.");
                continue;
            } else if (quantity.doubleValue() > availableQuantity.doubleValue()) {
                view.displayErrorMessage("Insufficient quantity. Available: " + availableQuantity);
                continue;
            }
            BigDecimal price = view.getBigDecimalNumericUserInput("Enter the sales price: ");
            if (price.doubleValue() <= 0) {
                view.displayErrorMessage("Enter a valid price.");
                continue;
            }

            salesOrder.add(new SalesOrder(selectedProduct, quantity, date.toString(), customerName, price));
            wantsToAddMoreItems = view.getYesNoUserInput("Do you want to add more items? (Y/N): ");
        }

        if (!salesOrder.isEmpty()) {
            //Update Inventory Quantity
            //  UpdateQuantity(order.getProduct().getId(), order.getOrderQuantity())
            for (SalesOrder soldItem : salesOrder) {
                model.updateProductQuantityById(soldItem.getProduct().getId(), soldItem.getOrderQuantity(),false);
            }
            model.addSalesOrderToOrderInventory(salesOrder);
        }

        view.displaySalesOrder(salesOrder);
        view.displayInventory(inventory);

    }
}
