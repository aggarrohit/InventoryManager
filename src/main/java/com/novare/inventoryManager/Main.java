package com.novare.inventoryManager;

import com.novare.inventoryManager.data.order.PurchaseOrder;
import com.novare.inventoryManager.data.order.PurchaseOrders;
import com.novare.inventoryManager.data.order.SalesOrder;
import com.novare.inventoryManager.data.order.SalesOrders;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderController;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderModel;
import com.novare.inventoryManager.purchaseOrder.PurchaseOrderView;
import com.novare.inventoryManager.saleOrder.SalesOrderController;
import com.novare.inventoryManager.saleOrder.SalesOrderModel;
import com.novare.inventoryManager.saleOrder.SalesOrderView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PurchaseOrderModel model = new PurchaseOrderModel();
        PurchaseOrderView view = new PurchaseOrderView();
        PurchaseOrderController controller = new PurchaseOrderController(model, view);



        // Create the purchase order
        controller.createPurchaseOrder();
        List<PurchaseOrders> purchaseOrders = model.getPurchaseOrderList();
        System.out.println();
        System.out.println("Purchase PurchaseOrder: ");
        for (int i = 0; i < purchaseOrders.size(); i++) {
            List<PurchaseOrder> purchaseOrder=purchaseOrders.get(i).getPurchaseOrder();
            System.out.println("PurchaseOrders ("+(i+1)+"): ");
            for (int j = 0; j < purchaseOrder.size(); j++) {
                PurchaseOrder order=purchaseOrder.get(j);
                System.out.println((j+1)+"-: ProductName: "+order.getProduct().getName() + "  ,Quantity: " +
                        order.getOrderQuantity()+ "("+order.getProduct().getMeasurement()+")"+
                        " ,From:"+ order.getCompanyName()+ " ,Date:"+order.getDate()+" ,Price: "+order.getPrice());
            }
        }


        SalesOrderModel model2 = new SalesOrderModel();
        SalesOrderView view2 = new SalesOrderView();
        SalesOrderController controller2 = new SalesOrderController(model2, view2);



        // Create the sales order
        controller2.createSalesOrder();
        List<SalesOrders> salesOrders = model2.getSalesOrderList();
        System.out.println();
        System.out.println("Sales SalesOrder: ");
        for (int i = 0; i < salesOrders.size(); i++) {
            List<SalesOrder> salesOrder=salesOrders.get(i).getSaleOrders();
            System.out.println("SalesOrders ("+(i+1)+"): ");
            for (int j = 0; j < salesOrder.size(); j++) {
                SalesOrder order=salesOrder.get(j);
                System.out.println((j+1)+"-: ProductName: "+order.getProduct().getName() + "  ,Quantity: " +
                        order.getOrderQuantity()+ "("+order.getProduct().getMeasurement()+")"+
                        " ,From:"+ order.getCustomerName()+ " ,Date:"+order.getDate()+" ,Price: "+order.getPrice());
            }
        }
    }
}
