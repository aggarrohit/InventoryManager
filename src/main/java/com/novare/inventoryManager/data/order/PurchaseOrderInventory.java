package com.novare.inventoryManager.data.order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PurchaseOrderInventory {
    private final List<PurchaseOrders> purchaseOrders=new ArrayList<>();

    public  List<PurchaseOrders> getPurchaseOrders() {
        return purchaseOrders;
    }

    public PurchaseOrders getPurchaseOrderById(UUID requestedId) throws NoSuchElementException {
        PurchaseOrders result = null;
        UUID id;
        for (PurchaseOrders purchaseList : purchaseOrders) {
            id = purchaseList.getOrderId();

            if (id == requestedId) {
                result = purchaseList;
            }
        }
        return result;
    }
    public PurchaseOrders getPurchaseOrderByIndex(int index) {
        return purchaseOrders.get(index);
    }
    public void addPurchaseOrder(PurchaseOrders purchaseOrder){
        purchaseOrders.add(purchaseOrder);
    }

    public int PurchaseOrderInventoryCount() {
        return purchaseOrders.size();
    }
}
