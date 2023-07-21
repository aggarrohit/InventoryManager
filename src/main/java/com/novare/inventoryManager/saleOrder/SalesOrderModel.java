package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.order.SalesOrder;
import com.novare.inventoryManager.order.SalesOrderInventory;
import com.novare.inventoryManager.product.Product;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
public class SalesOrderModel {
    private final SalesOrderInventory salesOrderInventory = new SalesOrderInventory();

    public synchronized void addSalesOrderToOrderInventory(List<SalesOrder> salesOrder) {
        salesOrderInventory.addSaleOrder(salesOrder);
    }
    public List<SalesOrder> getSalesOrderList(){
        return salesOrderInventory.getSaleOrders();
    }
    public List<Product> getInventoryProducts() throws FileNotFoundException {
        return InventoryFileHelper.getProducts();
    }

    public synchronized void updateProductQuantityById(UUID productId, BigDecimal quantity) {
        InventoryFileHelper.updateQuantity(productId, quantity);
    }
}
