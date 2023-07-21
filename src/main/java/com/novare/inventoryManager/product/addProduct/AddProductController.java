package com.novare.inventoryManager.product.addProduct;

import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.product.ProductHelper;

import java.math.BigDecimal;

public class AddProductController {

    final AddProductView addProductView;
    final AddProductModel addProductModel;
    private String productName;
    private Measurement measurement;
    private BigDecimal thresholdQuantity,price;

    public AddProductController(AddProductModel addProductModel,AddProductView addProductView) {
        this.addProductModel=addProductModel;
        this.addProductView=addProductView;

        proceedForProductInputs();

    }

    private void proceedForProductInputs(){
        requestProductName();
        askMeasurement();
        askThresholdQuantity();
        askPrice();
        addProductModel.addProductToInventory(productName,measurement,thresholdQuantity,price);
        addProductView.showSuccessMessage();
    }



    private void requestProductName() {
        addProductView.requestProductName();
        try {
            checkProductName();
        }catch (IllegalArgumentException e){
            addProductView.printExceptionMessage(e);
            requestProductName();
        }
    }

    private void checkProductName() throws IllegalArgumentException{
        String productName = addProductModel.getTextInput();
        if(ProductHelper.getProductNameShortcomings(productName).size()>0) throw new IllegalArgumentException("Invalid product name");
        if(addProductModel.doesProductExist(productName))  throw new IllegalArgumentException("Product already exists!!");
        this.productName = productName;
    }

    private void askMeasurement() {
        addProductView.requestProductMeasurement(Measurement.getValues().toString());

        try {
            String measurementString = addProductModel.getTextInput();
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
            this.thresholdQuantity = addProductModel.getNumberInput();
        }catch (NumberFormatException e){
            addProductView.showInvalidInput();
            askThresholdQuantity();
        }
    }

    private void askPrice() {
        addProductView.requestProductPrice();
        try {
            this.price = addProductModel.getNumberInput();
        }catch (NumberFormatException e){
            addProductView.showInvalidInput();
            askPrice();
        }
    }


}
