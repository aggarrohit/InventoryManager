package com.novare.inventoryManager.product.addProduct;

import com.novare.inventoryManager.utils.ConsoleMessage;

public class AddProductView {
    public void printExceptionMessage(Exception exception){
        System.out.println(exception.getMessage());
    }

    public void requestProductName() {
        System.out.print("Product Name: ");
    }

    public void requestProductMeasurement(String options) {
        System.out.print("Measurement "+ options+": ");
    }

    public void requestProductThresholdQuantity() {
        System.out.print("Threshold quantity: ");
    }

    public void requestProductPrice() {
        System.out.print("Selling Price: ");
    }

    public void showInvalidInput() {
        ConsoleMessage.showErrorMessage("Invalid input!!");
    }

    public void showSuccessMessage() {
        ConsoleMessage.showSuccessMessage("Product added successfully!!");
    }
}
