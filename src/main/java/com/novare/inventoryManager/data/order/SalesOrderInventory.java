package com.novare.inventoryManager.data.order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class SalesOrderInventory {
    private final List<SalesOrders> salesOrders =new ArrayList<>();

    public  List<SalesOrders> getSaleOrders() {
        return salesOrders;
    }

    public SalesOrders getSaleOrderById(UUID requestedId) throws NoSuchElementException {
        SalesOrders result = null;
        UUID id;
        for (SalesOrders saleList : salesOrders) {
            id = saleList.getOrderId();

            if (id == requestedId) {
                result = saleList;
            }
        }
        return result;
    }
    public SalesOrders getSaleOrderByIndex(int index) {
        return salesOrders.get(index);
    }
    public void addSaleOrder(SalesOrders saleOrder){
        salesOrders.add(saleOrder);
    }

    public int SaleOrderInventoryCount() {
        return salesOrders.size();
    }
}
