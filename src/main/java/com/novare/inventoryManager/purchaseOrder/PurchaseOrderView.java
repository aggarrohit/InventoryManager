package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.data.inventory.Measurement;
import com.novare.inventoryManager.data.inventory.Product;

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
            System.out.println(i + 1 + ". " + product.getName() + " (Quantity: " + product.getQuantity()
                    +" "+product.getMeasurement()+" )");
        }
    }
    public boolean getYesNoUserInput(String prompt) {
        String choice = getInput(prompt);
        return choice.equalsIgnoreCase("Y");
    }
    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public int getIntNumericUserInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            displayInvalidInputMessage();
        }
    return 0;
    }

    public Measurement getMeasureTypeInput() {
        int choice = getIntNumericUserInput("Enter measure type id between 1 and 3 (1=Quantity, 2=Liters, 3=Kilogram): ");
        switch (choice) {
            case 1 -> {
                return Measurement.Pieces;
            }
            case 2 -> {
                return Measurement.Liter;
            }
            case 3 -> {
                return Measurement.Kilogram;
            }
            default -> {
                displayInvalidInputMessage();
                return getMeasureTypeInput();
            }
        }
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
    public void displayInvalidInputMessage() {
        System.out.println("⚠️ Invalid input. Please try again.\n");
    }
    public void displayErrorMessage(String errorMessage) {
        System.out.println("⚠️"+errorMessage);
    }
}
