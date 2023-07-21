package com.novare.inventoryManager.product.addProduct;

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
        System.out.println("Invalid input!!");
    }

    public void showSuccessMessage() {
        System.out.println("Product added successfully!!");
    }
}
