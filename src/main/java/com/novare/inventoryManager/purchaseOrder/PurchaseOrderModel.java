package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.data.inventory.Inventory;
import com.novare.inventoryManager.data.inventory.Product;
import com.novare.inventoryManager.data.order.PurchaseOrder;
import com.novare.inventoryManager.data.order.PurchaseOrders;
import com.novare.inventoryManager.data.order.PurchaseOrderInventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PurchaseOrderModel {
    private final PurchaseOrderInventory purchaseOrderInventory=new PurchaseOrderInventory();

    public void addPurchaseOrderToOrderInventory(List<PurchaseOrder> purchaseOrder) {
        purchaseOrderInventory.addPurchaseOrder(new PurchaseOrders(purchaseOrder));
    }
    public List<PurchaseOrders> getPurchaseOrderList(){
        return purchaseOrderInventory.getPurchaseOrders();
    }

    public List<Product> getInventoryProducts() {
        return Inventory.getProducts();
    }
    public void updateProductQuantityById(UUID productId, BigDecimal newQuantity,Boolean isPurchase) {
        Inventory.updateProductQuantityById(productId,newQuantity,true);
    }
}
