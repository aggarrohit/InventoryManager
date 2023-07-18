package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.data.inventory.Product;
import com.novare.inventoryManager.data.order.SalesOrder;

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
            System.out.println(i + 1 + ". " + product.getName() + " (Quantity: " + product.getQuantity() + ")");
        }
        System.out.println();
    }
    public BigDecimal getBigDecimalNumericUserInput(String prompt) {
        System.out.print(prompt+": ");
        try {
            return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
        }
        return null;
    }
    public void displayInventoryItem(Product product) {
        System.out.println("Item: " + product.getName());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("PurchasePrice: " + product.getPrice());
    }

    public boolean getYesNoUserInput(String prompt) {
        String choice = getInput(prompt);
        return choice.equalsIgnoreCase("Y");
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntNumericUserInput(String prompt) {
        System.out.print(prompt);
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
            System.out.println("- " + product.getName() + " (Quantity: " + soldItem.getOrderQuantity() + ")");
        }
    }

    public void displayInvalidInputMessage() {
        System.out.println("⚠️ Invalid input. Please try again.\n");
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println("⚠️ " + errorMessage);
    }
}