package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.order.PurchaseOrder;
import com.novare.inventoryManager.order.PurchaseOrderInventory;
import com.novare.inventoryManager.product.Product;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PurchaseOrderModel {
    private final PurchaseOrderInventory purchaseOrderInventory = new PurchaseOrderInventory();

    public synchronized void addPurchaseOrderToOrderInventory(List<PurchaseOrder> purchaseOrder) {
        purchaseOrderInventory.addPurchaseOrder(purchaseOrder);
    }

    public List<PurchaseOrder> getPurchaseOrderList() {
        return purchaseOrderInventory.getPurchaseOrders();
    }

    public List<Product> getInventoryProducts() throws FileNotFoundException {
        return InventoryFileHelper.getProducts();
    }

    public void updateProductQuantityById(UUID productId, BigDecimal newQuantity) {
        InventoryFileHelper.updateQuantity(productId, newQuantity);
    }
}
