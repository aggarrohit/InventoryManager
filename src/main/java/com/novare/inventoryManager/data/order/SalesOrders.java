package com.novare.inventoryManager.data.order;

import java.util.List;
import java.util.UUID;

public class SalesOrders {
    private static List<SalesOrder> salesOrders;
    private final UUID orderId;

    public SalesOrders(List<SalesOrder> salesOrders) {
        this.orderId=UUID.randomUUID();
        SalesOrders.salesOrders = salesOrders;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<SalesOrder> getSaleOrders() {
        return salesOrders;
    }

    public void addOrder(SalesOrder salesOrder) {
        salesOrders.add(salesOrder);
    }
}
