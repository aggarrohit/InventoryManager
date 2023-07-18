package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.data.order.PurchaseOrder;
import com.novare.inventoryManager.data.inventory.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderController {
    private final PurchaseOrderModel model;
    private final PurchaseOrderView view;

    public PurchaseOrderController(PurchaseOrderModel model, PurchaseOrderView view) {
        this.model = model;
        this.view = view;
    }

    public void createPurchaseOrder() {
        List<PurchaseOrder> purchaseOrder = new ArrayList<>();
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
                break;
            }
            BigDecimal quantity = view.getBigDecimalNumericUserInput("Enter the quantity");
            if (quantity == null) break;
            String companyName = view.getInput("Enter the Company name: ");
            Product selectedProduct = inventory.get(itemNumber - 1);
            purchaseOrder.add(new PurchaseOrder(selectedProduct, quantity, date.toString(), companyName, selectedProduct.getPrice()));
            wantsToAddMoreItems = view.getYesNoUserInput("Do you want to add more items? (Y/N): ");
        }
        if (!purchaseOrder.isEmpty()) {
            //Update Inventory Quantity
            //  UpdateQuantity(order.getProduct().getId(), order.getOrderQuantity())
            for (PurchaseOrder order:
                 purchaseOrder) {
                model.updateProductQuantityById(order.getProduct().getId(), order.getOrderQuantity(),true);
            }
            model.addPurchaseOrderToOrderInventory(purchaseOrder);
        }
        view.displayInventory(inventory);
    }
}
