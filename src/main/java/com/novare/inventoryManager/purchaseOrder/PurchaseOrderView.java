package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.order.PurchaseOrder;
import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.inventory.Measurement;


import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PurchaseOrderView {
    private final Scanner scanner;

    public PurchaseOrderView() {
        scanner = new Scanner(System.in);
    }

    public void displayInventory(List<Product> inventoryProducts) {
        System.out.println("Inventory:");
        for (int i = 0; i < inventoryProducts.size(); i++) {
            Product product = inventoryProducts.get(i);
            System.out.println(i + 1 + ". " + product.product_name() + " (Quantity: " + product.quantity() +
                    " , Sales price: " + product.price() +")");
        }
    }
    public boolean getYesNoUserInput(String prompt) {
        String choice;
        do {
            choice = getInput(prompt);
        } while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N"));
        return choice.equalsIgnoreCase("Y");
    }
    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public int getIntNumericUserInput(String prompt) {
        System.out.print(prompt+" :");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
        }
    return 0;
    }
    public BigDecimal getBigDecimalNumericUserInput(String prompt) {
        System.out.print(prompt+" :");
        try {
            return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
        }
        return null;
    }
    public void displayInvalidInputMessage() {
        System.out.println("⚠️ Invalid input. Please try again.");
    }
    public void displayInputMessage(String prompt) {
        System.out.println(prompt);
    }
    public void displayErrorMessage(String errorMessage) {
        System.out.println("⚠️"+errorMessage);
    }

    public void displayOrderInventory(List<PurchaseOrder> inventoryPurchaseOrder) {
        int orderGroup=0;
        String orderId="";
        for (int i = 0; i < inventoryPurchaseOrder.size(); i++) {
            PurchaseOrder purchaseOrder = inventoryPurchaseOrder.get(i);
            if (!(orderId.equals(purchaseOrder.getOrderId().toString()))){
                orderGroup++;
                System.out.println("Order  " +orderGroup+" :");
            }
            System.out.println(purchaseOrder.getProduct().product_name() + " ( Quantity: "
                    + purchaseOrder.getOrderQuantity() + " " +purchaseOrder.getProduct().measurement()
                    + " , Date: " + purchaseOrder.getDate()
                    + " , From : " + purchaseOrder.getCompanyName()
                    + " , price: " + purchaseOrder.getPrice()
                    +")");
            orderId=purchaseOrder.getOrderId().toString();
        }
    }
}
