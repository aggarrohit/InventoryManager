package com.novare.inventoryManager.data.order;

import java.util.List;
import java.util.UUID;

public class PurchaseOrders {
    private static List<PurchaseOrder> purchaseOrder;
    private final UUID orderId;

    public PurchaseOrders(List<PurchaseOrder> purchaseOrder) {
        this.orderId=UUID.randomUUID();
        PurchaseOrders.purchaseOrder = purchaseOrder;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void addOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrders.purchaseOrder.add(purchaseOrder);
    }
}
