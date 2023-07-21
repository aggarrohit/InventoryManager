package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.order.PurchaseOrder;
import com.novare.inventoryManager.product.Product;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PurchaseOrderController {
    private final PurchaseOrderModel model;
    private final PurchaseOrderView view;

    public PurchaseOrderController(PurchaseOrderModel model, PurchaseOrderView view) {
        this.model = model;
        this.view = view;
    }

    public void createPurchaseOrder() throws FileNotFoundException {
        List<PurchaseOrder> purchaseOrder = Collections.synchronizedList(new ArrayList<>());
        List<Product> inventory = model.getInventoryProducts();
        view.displayInventory(inventory);
        boolean wantsToAddMoreItems = true;
        LocalDate date = LocalDate.now();
        UUID orderId=UUID.randomUUID();
        while (wantsToAddMoreItems) {
            inventory = model.getInventoryProducts();
            int itemNumber;
            Product selectedProduct;
            BigDecimal quantity;
            String companyName;
            BigDecimal price;
            // Get valid item number
            while (true) {
                itemNumber = view.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""");
                if (itemNumber <= 0 || itemNumber > inventory.size()) {
                    view.displayErrorMessage("Enter a valid number between 1 and " + inventory.size());
                } else {
                    selectedProduct = inventory.get(itemNumber - 1);
                    break;
                }
            }
            // Get valid quantity
            while (true) {
                quantity = view.getBigDecimalNumericUserInput("Enter the quantity");
                if (quantity == null || quantity.doubleValue() <= 0) {
                    view.displayErrorMessage("Enter a valid quantity.");
                } else {
                    break;
                }
            }
            // Get valid company name
            while (true) {
                companyName = view.getInput("Enter the Company name");
                if (companyName.isEmpty()) {
                    view.displayErrorMessage("Enter a valid company name.");
                } else {
                    break;
                }
            }
            // Get valid price
            while (true) {
                price = view.getBigDecimalNumericUserInput("Enter the purchase price");
                if (price == null || price.doubleValue() <= 0) {
                    view.displayErrorMessage("Enter a valid price.");
                } else {
                    break;
                }
            }

            purchaseOrder.add(new PurchaseOrder(orderId,selectedProduct, quantity, date.toString(), companyName, price));
            model.updateProductQuantityById(selectedProduct.id(), selectedProduct.quantity().add(quantity));
            wantsToAddMoreItems = view.getYesNoUserInput("Do you want to add more items? (Y/N)");
        }
        if (!purchaseOrder.isEmpty()) {
            model.addPurchaseOrderToOrderInventory(purchaseOrder);
            view.displayInputMessage("Purchase order submitted Successfully. You return to the previous menu");
        }
    }
    public void printPurchaseOrdersInInventory() {
        List<PurchaseOrder> purchaseOrders = model.getPurchaseOrderList();
        if (purchaseOrders.isEmpty()) {
            view.displayInputMessage("\nPurchase order inventory is empty.");
        } else {
            view.displayInputMessage("\nPurchase orders in the inventory:");
                view.displayOrderInventory(purchaseOrders);
        }
    }
}
