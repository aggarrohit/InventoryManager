package com.novare.inventoryManager.product;

import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.inventory.Measurement;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class AddProductController {

    AddProductView addProductView;
    AddProductModel addProductModel;

    Scanner scanner;

    private String productName;
    private Measurement measurement;
    private BigDecimal thresholdQuantity,price;

    public AddProductController(AddProductModel addProductModel,AddProductView addProductView) {
        this.addProductModel=addProductModel;
        this.addProductView=addProductView;

        scanner = new Scanner(System.in);

        proceedForProductInputs();

    }

    private void proceedForProductInputs(){
        Inventory.listInventory();
        requestProductName();
        askMeasurement();
        askThresholdQuantity();
        askPrice();
        createProduct();
        addProductView.showSuccessMessage();
        Inventory.listInventory();
    }

    private void createProduct() {
        Product product = new Product(  UUID.randomUUID(),
                                        productName,
                                        measurement,
                                        BigDecimal.ZERO,
                                        thresholdQuantity,
                                        price
                                     );
        Inventory.addProductToInventory(product);
    }

    private void requestProductName() {
        addProductView.requestProductName();
        try {
            readTextInput();
            checkProductName();
        }catch (IllegalArgumentException e){
            addProductView.printExceptionMessage(e);
            requestProductName();
        }
    }

    private void checkProductName() throws IllegalArgumentException{
        String productName = addProductModel.getEnteredText();
        if(ProductHelper.getProductNameShortcomings(productName).size()>0) throw new IllegalArgumentException("Invalid product name");
        if(Inventory.doesProductExist(productName))  throw new IllegalArgumentException("Product already exists!!");
        this.productName = productName;
    }

    private void askMeasurement() {
        addProductView.requestProductMeasurement(Measurement.getValues().toString());

        try {
            readTextInput();
            String measurementString = addProductModel.getEnteredText();
            if(!Measurement.getValues().contains(measurementString)) throw new Exception();
            this.measurement = Measurement.getMeasurementByValue(measurementString);
        }catch (Exception e){
            addProductView.showInvalidInput();
            askMeasurement();
        }
    }

    private void askThresholdQuantity() {
        addProductView.requestProductThresholdQuantity();
        try {
            readNumberInput();
            BigDecimal thresholdQuantity;
            thresholdQuantity = new BigDecimal(addProductModel.getEnteredNumber().toString());
            this.thresholdQuantity = thresholdQuantity;
        }catch (NumberFormatException e){
            addProductView.showInvalidInput();
            askThresholdQuantity();
        }
    }

    private void askPrice() {
        addProductView.requestProductPrice();
        try {
            readNumberInput();
            BigDecimal price;
            price = new BigDecimal(addProductModel.getEnteredNumber().toString());
            this.price = price;
        }catch (NumberFormatException e){
            addProductView.showInvalidInput();
            askPrice();
        }
    }

    private void readTextInput() throws IllegalArgumentException{
        String input = scanner.nextLine();
        try {
            if(input.trim().isBlank()) throw new IllegalArgumentException("Nothing Entered");
            addProductModel.setEnteredText(input.trim());
        }catch (IllegalArgumentException exception){
            addProductView.printExceptionMessage(exception);
            addProductModel.setEnteredText("");
            throw new IllegalArgumentException("Nothing Entered");
        }
    }

    private void readNumberInput() throws NumberFormatException{
        String input = scanner.nextLine();
        try {
            addProductModel.setEnteredNumber(new BigDecimal(input.trim()));
            if(new BigDecimal(input.trim()).compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
        }catch (NumberFormatException exception){
            addProductModel.setEnteredNumber(BigDecimal.valueOf(-1));
            throw new NumberFormatException();
        }
    }
}
