package com.novare.inventoryManager.data.order;

import java.io.*;
import java.util.*;

public class SalesOrderInventory {
    private static final String PATH_TO_SalesOrderINVENTORY_FILE = "salesOrderInventory.txt";
    private final List<SalesOrders> salesOrders = Collections.synchronizedList(new ArrayList<>());

    public SalesOrderInventory() {
     //   loadSalesOrdersFromFile(PATH_TO_SalesOrderINVENTORY_FILE);
    }

    public List<SalesOrders> getSaleOrders() {
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

    public synchronized void addSaleOrder(SalesOrders saleOrder) {
        salesOrders.add(saleOrder);
        //saveSalesOrdersToFile(PATH_TO_SalesOrderINVENTORY_FILE);
    }
    public int SaleOrderInventoryCount() {
        return salesOrders.size();
    }

    // Method to save all sales orders to a text file
}
