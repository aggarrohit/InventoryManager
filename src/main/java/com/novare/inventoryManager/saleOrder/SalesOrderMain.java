package com.novare.inventoryManager.saleOrder;

import java.io.FileNotFoundException;

public class SalesOrderMain {
    public SalesOrderMain(String function) throws FileNotFoundException {
        SalesOrderModel model=new SalesOrderModel();
        SalesOrderView view=new SalesOrderView();
        SalesOrderController controller=new SalesOrderController(model,view);
        if (function.equalsIgnoreCase("createSalesOrder")) controller.createSalesOrder();
        if (function.equalsIgnoreCase("showSalesOrders")) controller.printSalesOrdersInInventory();
    }
}
