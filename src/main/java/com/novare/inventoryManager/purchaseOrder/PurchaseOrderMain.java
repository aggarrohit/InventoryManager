package com.novare.inventoryManager.purchaseOrder;

import java.io.FileNotFoundException;

public class PurchaseOrderMain {
    public PurchaseOrderMain(String function) throws FileNotFoundException {
        PurchaseOrderModel model=new PurchaseOrderModel();
        PurchaseOrderView view=new PurchaseOrderView();
        PurchaseOrderController controller=new PurchaseOrderController(model,view);
        if (function.equalsIgnoreCase("createPurchaseOrder")) controller.createPurchaseOrder();
        if (function.equalsIgnoreCase("showPurchaseOrders")) controller.printPurchaseOrdersInInventory();
    }
}
