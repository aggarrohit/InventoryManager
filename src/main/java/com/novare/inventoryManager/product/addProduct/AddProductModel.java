package com.novare.inventoryManager.product.addProduct;

import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class AddProductModel {

    final Scanner scanner;

    public AddProductModel(){
        scanner = new Scanner(System.in);
    }

    public String getTextInput() throws IllegalArgumentException{
        String input = scanner.nextLine();
        if(input.trim().isBlank()) throw new IllegalArgumentException("Nothing Entered");
        return input.trim();

    }

    public BigDecimal getNumberInput() throws NumberFormatException{
        String input = scanner.nextLine();
        if(new BigDecimal(input.trim()).compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
        return new BigDecimal(input.trim());
    }

    public void addProductToInventory(Product product){
        Inventory.addProductToInventory(product);
    }

    public boolean doesProductExist(String productName) {
        return Inventory.doesProductExist(productName);
    }

    public void addProductToInventory(String productName, Measurement measurement, BigDecimal thresholdQuantity, BigDecimal price){
        Product product = new Product(  UUID.randomUUID(),
                productName,
                measurement,
                BigDecimal.ZERO,
                thresholdQuantity,
                price
        );
        Inventory.addProductToInventory(product);
    }
}
