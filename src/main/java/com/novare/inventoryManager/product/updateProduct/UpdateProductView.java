package com.novare.inventoryManager.product.updateProduct;

import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.utils.ConsoleMessage;

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
        ConsoleMessage.showErrorMessage("Invalid input!!");
    }

    public void showSuccessMessage() {
        ConsoleMessage.showSuccessMessage("Product updated successfully!!");
    }

    public void listInventory(){
        Inventory.listInventory();
    }
}
