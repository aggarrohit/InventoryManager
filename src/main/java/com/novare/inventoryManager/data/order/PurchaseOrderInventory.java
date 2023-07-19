package com.novare.inventoryManager.data.order;

import java.util.*;

public class PurchaseOrderInventory {
    private final List<PurchaseOrders> purchaseOrders= Collections.synchronizedList(new ArrayList<>());

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
    public synchronized void addPurchaseOrder(PurchaseOrders purchaseOrder){
        purchaseOrders.add(purchaseOrder);
    }

    public int PurchaseOrderInventoryCount() {
        return purchaseOrders.size();
    }
}
