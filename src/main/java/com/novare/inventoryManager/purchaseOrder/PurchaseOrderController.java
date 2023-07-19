package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.data.order.PurchaseOrder;
import com.novare.inventoryManager.product.Product;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


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
        while (wantsToAddMoreItems) {
            int itemNumber = view.getIntNumericUserInput("""
                    Purchase order details
                    Enter the item number:\s""");
            if (itemNumber <= 0 || itemNumber > inventory.size()) {
                view.displayErrorMessage("Enter a valid number between 1 and " + inventory.size());
                continue;
            }
            Product selectedProduct = inventory.get(itemNumber - 1);
            BigDecimal quantity = view.getBigDecimalNumericUserInput("Enter the quantity");
            if (quantity == null || quantity.doubleValue() <= 0) {
                view.displayErrorMessage("Enter a valid quantity.");
                continue;
            }
            String companyName = view.getInput("Enter the Company name: ");
            BigDecimal price = view.getBigDecimalNumericUserInput("\n The sales price is , "
                    +selectedProduct.price() +"\n Enter the purchase price:");
            if (price.doubleValue() <= 0) {
                view.displayErrorMessage("Enter a valid price.");
                continue;
            }
            purchaseOrder.add(new PurchaseOrder(selectedProduct, quantity, date.toString(), companyName, price));
            wantsToAddMoreItems = view.getYesNoUserInput("Do you want to add more items? (Y/N): ");
        }
        if (!purchaseOrder.isEmpty()) {

            //Update Inventory Quantity
            //  UpdateQuantity(order.getProduct().getId(), order.getOrderQuantity())
            for (PurchaseOrder order :
                    purchaseOrder) {
                    model.updateProductQuantityById(order.getProduct().id(),
                            order.getProduct().quantity().add(order.getOrderQuantity()));
            }
            model.addPurchaseOrderToOrderInventory(purchaseOrder);
        }
        view.displayInventory(inventory);
    }
}
