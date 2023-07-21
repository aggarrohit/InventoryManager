package com.novare.inventoryManager.product.updateProduct;

import com.novare.inventoryManager.inventory.Inventory;

public class UpdateProductView {
    public void requestProductNumber(String fieldToUpdate) {
        System.out.print("Product Number to update "+fieldToUpdate.replaceAll("_"," ")+": ");
    }

    public void requestProductThresholdQuantity() {
        System.out.print("New threshold quantity: ");
    }

    public void requestProductPrice() {
        System.out.print("New selling Price: ");
    }

    public void showInvalidInput() {
        System.out.println("Invalid input!!");
    }

    public void showSuccessMessage() {
        System.out.println("Product updated successfully!!");
    }

    public void listInventory(){
        Inventory.listInventory();
    }
}
