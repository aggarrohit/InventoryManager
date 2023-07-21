package com.novare.inventoryManager.product.updateProduct;

import com.novare.inventoryManager.product.Product;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static com.novare.inventoryManager.inventory.Constants.PRODUCT_PRICE;
import static com.novare.inventoryManager.inventory.Constants.PRODUCT_THRESHOLD_QUANTITY;

public class UpdateProductController {

    private final UpdateProductView updateProductView;
    private final UpdateProductModel updateProductModel;

    private final String fieldToUpdate;
    private Product product;
    private final List<Product> products;

    public UpdateProductController(UpdateProductModel updateProductModel, UpdateProductView updateProductView,String fieldToUpdate)
                                    throws FileNotFoundException {
        this.updateProductModel=updateProductModel;
        this.updateProductView=updateProductView;
        this.fieldToUpdate=fieldToUpdate;

        updateProductView.listInventory();
        products = updateProductModel.getProducts();
        requestProductNumber();

    }


    private void requestProductNumber() {
        updateProductView.requestProductNumber(fieldToUpdate);
        try {
            product = products.get(updateProductModel.getNumberInput().intValue()-1);
            askUpdatedValue();
        }catch (NumberFormatException | IndexOutOfBoundsException e){
            updateProductView.showInvalidInput();
            requestProductNumber();
        }
    }

    private void askUpdatedValue() {
        if(fieldToUpdate.equals(PRODUCT_PRICE)){
            askPrice();
        }else
        if(fieldToUpdate.equals(PRODUCT_THRESHOLD_QUANTITY)){
            askThresholdQuantity();
        }else{
            throw new IllegalArgumentException();
        }
    }


    private void askThresholdQuantity() {
        updateProductView.requestProductThresholdQuantity();
        try {
            BigDecimal thresholdQuantity = new BigDecimal(updateProductModel.getNumberInput().toString());
            updateProductModel.updateThresholdQuantity(product.id(), thresholdQuantity);
            updateProductView.showSuccessMessage();
        }catch (NumberFormatException e){
            updateProductView.showInvalidInput();
            askThresholdQuantity();
        }
    }

    private void askPrice() {
        updateProductView.requestProductPrice();
        try {
            BigDecimal price = new BigDecimal(updateProductModel.getNumberInput().toString());
            updateProductModel.updatePrice(product.id(), price);
            updateProductView.showSuccessMessage();
        }catch (NumberFormatException e){
            updateProductView.showInvalidInput();
            askPrice();
        }
    }


}
