package com.novare.inventoryManager.order;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.utils.ConsoleMessage;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class PurchaseOrderInventory {
    private final String Purchase_Order_PATH = System.getProperty("user.dir") + "/data/PurchaseOrder.txt";

    private final List<PurchaseOrder> purchaseOrders = Collections.synchronizedList(loadPurchaseOrdersFromFileTxt());

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public List<PurchaseOrder> getPurchaseOrderById(UUID requestedId) throws NoSuchElementException {
        List<PurchaseOrder> result = new ArrayList<>();
        for (PurchaseOrder purchaseList : purchaseOrders) {
            if (purchaseList.getOrderId().equals(requestedId)) {
                result.add(purchaseList);
            }
        }
        return result;
    }

    public PurchaseOrder getPurchaseOrderByIndex(int index) {
        return purchaseOrders.get(index);
    }

    public synchronized void addPurchaseOrder(List<PurchaseOrder> purchaseOrder) {
        for (PurchaseOrder purchaseOrder1 : purchaseOrder) {
            purchaseOrders.add(purchaseOrder1);
        }
        savePurchaseOrdersToFileTxt(purchaseOrder);
    }

    public int PurchaseOrderInventoryCount() {
        return purchaseOrders.size();
    }

    public List<PurchaseOrder> loadPurchaseOrdersFromFileTxt() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Purchase_Order_PATH));
            List<PurchaseOrder> listOfPurchasesOrders = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (!nextLine.isEmpty()) {
                    PurchaseOrder purchasesOrder = convertStringToPurchaseOrders(nextLine);
                    listOfPurchasesOrders.add(purchasesOrder);
                }
            }
            sc.close();
            return listOfPurchasesOrders;
        } catch (FileNotFoundException e) {
            ConsoleMessage.showErrorMessage("PurchaseOrder Inventory file not found.");
        }
        return new ArrayList<>();
    }

    private PurchaseOrder convertStringToPurchaseOrders(String purchasesOrderString) {
        String[] purchasesOrder = purchasesOrderString.split(",");
        return new PurchaseOrder(UUID.fromString(purchasesOrder[0]),
                InventoryFileHelper.getProductByProductId(UUID.fromString(purchasesOrder[1])),
                new BigDecimal(purchasesOrder[3]),
                purchasesOrder[4],
                purchasesOrder[5],
                new BigDecimal(purchasesOrder[6])
        );
    }

    public void savePurchaseOrdersToFileTxt(List<PurchaseOrder> purchaseOrders) throws IllegalArgumentException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Purchase_Order_PATH, true));
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                String line = purchaseOrderToString(purchaseOrder);
                writer.write(line + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private String purchaseOrderToString(PurchaseOrder purchaseOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(purchaseOrder.getOrderId().toString()).append(",");
        sb.append(purchaseOrder.getProduct().id().toString()).append(",");
        sb.append(purchaseOrder.getProduct().measurement().toString()).append(",");
        sb.append(purchaseOrder.getOrderQuantity()).append(",");
        sb.append(purchaseOrder.getDate()).append(",");
        sb.append(purchaseOrder.getCompanyName()).append(",");
        sb.append(purchaseOrder.getPrice());
        return sb.toString();
    }

}




