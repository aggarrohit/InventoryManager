package com.novare.inventoryManager.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novare.inventoryManager.order.PurchaseOrder;
import com.novare.inventoryManager.order.PurchaseOrderInventory;
import com.novare.inventoryManager.order.SalesOrder;
import com.novare.inventoryManager.order.SalesOrderInventory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegrationImpl implements IntegrationInterface {

    private  List<IntegrationFile> integrationFile= Collections.synchronizedList(new ArrayList<>());
    private final SalesOrderInventory salesOrderInventory = new SalesOrderInventory();
    private final PurchaseOrderInventory purchaseOrderInventory = new PurchaseOrderInventory();
    public final String Export_File_PATH = System.getProperty("user.dir") + "/data/Export/Export.json";


    @Override
    public void createIntegrateFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this.getIntegrateFile());
            // Save the JSON string to a file
            FileWriter fileWriter = new FileWriter(Export_File_PATH);
            fileWriter.write(json);
            fileWriter.close();
            System.out.println("Json File is created in the Export directory." +
                    "\nYou will return to the previous menu");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<IntegrationFile> getIntegrateFile() {
        List<PurchaseOrder> purchaseOrderList=purchaseOrderInventory.getPurchaseOrders();
        List<SalesOrder> salesOrderList=salesOrderInventory.getSaleOrders();
        for (PurchaseOrder purchaseOrder:purchaseOrderList){
            integrationFile.add(new IntegrationFile("Purchase",purchaseOrder.getOrderId(),purchaseOrder.getProduct().id(),
                    purchaseOrder.getProduct().product_name(),purchaseOrder.getProduct().measurement(),
                    purchaseOrder.getOrderQuantity(),purchaseOrder.getDate(),purchaseOrder.getCompanyName(),
                    purchaseOrder.getPrice()));
        }
        for (SalesOrder salesOrder:salesOrderList){
            integrationFile.add(new IntegrationFile("Sales",salesOrder.getOrderId(),salesOrder.getProduct().id(),
                    salesOrder.getProduct().product_name(),salesOrder.getProduct().measurement(),
                    salesOrder.getOrderQuantity(),salesOrder.getDate(),salesOrder.getCustomerName(),
                    salesOrder.getPrice()));
        }
        return integrationFile;
    }
}
