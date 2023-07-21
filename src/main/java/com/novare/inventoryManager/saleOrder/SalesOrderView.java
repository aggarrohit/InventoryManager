package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.order.SalesOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
public class SalesOrderView {
    private final Scanner scanner;

    public SalesOrderView() {
        scanner = new Scanner(System.in);
    }

    public void displayInventory(List<Product> inventoryProducts) {
        System.out.println();
        System.out.println("Inventory:");
        for (int i = 0; i < inventoryProducts.size(); i++) {
            Product product = inventoryProducts.get(i);
            System.out.println(i + 1 + ". " + product.product_name() + " (Quantity: " + product.quantity() +
                    " , Sales price: " + product.price() +")");
        }
        System.out.println();
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
    public void displayInventoryItem(Product product) {
        System.out.println("Item: " + product.product_name());
        System.out.println("Quantity: " + product.quantity());
        System.out.println("SalesPrice: " + product.price());
    }

    public boolean getYesNoUserInput(String prompt) {
        String choice;
        do {
            choice = getInput(prompt);
        } while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N"));
        return choice.equalsIgnoreCase("Y");
    }

    public String getInput(String prompt) {
        System.out.print(prompt+" :");
        return scanner.nextLine();
    }

    public int getIntNumericUserInput(String prompt) {
        System.out.print(prompt+" :");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
            return getIntNumericUserInput(prompt);
        }
    }

    public void displaySalesOrder(List<SalesOrder> salesOrder) {
        System.out.println("\nSales Order:");
        for (SalesOrder soldItem : salesOrder) {
            System.out.println("Customer: " + soldItem.getCustomerName());
            System.out.println("Items:");
            Product product = soldItem.getProduct();
            System.out.println("- " + product.product_name() + " (Quantity: " + soldItem.getOrderQuantity() + ")");
        }
    }

    public void displayInvalidInputMessage() {
        System.out.println("⚠️ Invalid input. Please try again.");
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println("⚠️ " + errorMessage);
    }

    public void displayInputMessage(String prompt) {
        System.out.println(prompt);
    }

    public void displayOrderInventory(List<SalesOrder> inventorySaleOrder) {
        int orderGroup=0;
        String orderId="";
        for (int i = 0; i < inventorySaleOrder.size(); i++) {
            SalesOrder salesOrder = inventorySaleOrder.get(i);
            if (!(orderId.equals(salesOrder.getOrderId().toString()))){
                orderGroup++;
                System.out.println("Order  " +orderGroup+" :");
            }
            System.out.println(salesOrder.getProduct().product_name() + " ( Quantity: "
                    + salesOrder.getOrderQuantity() + " " +salesOrder.getProduct().measurement()
                    + " , Date: " + salesOrder.getDate()
                    + " , To : " + salesOrder.getCustomerName()
                    + " , price: " + salesOrder.getPrice()
                    +")");
            orderId=salesOrder.getOrderId().toString();
        }
    }
}
