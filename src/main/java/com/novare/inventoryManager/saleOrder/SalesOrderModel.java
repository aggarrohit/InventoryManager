package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.data.inventory.Inventory;
import com.novare.inventoryManager.data.inventory.Product;
import com.novare.inventoryManager.data.order.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
public class SalesOrderModel {
    private final SalesOrderInventory salesOrderInventory = new SalesOrderInventory();

    public synchronized void addSalesOrderToOrderInventory(List<SalesOrder> salesOrder) {
        salesOrderInventory.addSaleOrder(new SalesOrders(salesOrder));
    }
    public List<SalesOrders> getSalesOrderList(){
        return salesOrderInventory.getSaleOrders();
    }
    public List<Product> getInventoryProducts() {
        return Inventory.getProducts();
    }

    public synchronized void updateProductQuantityById(UUID productId, BigDecimal quantity) {
        Inventory.updateProductQuantityById(productId, quantity);
    }
}
