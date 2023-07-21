package com.novare.inventoryManager.product.updateProduct;

import java.io.FileNotFoundException;

public class UpdateProduct {
    public UpdateProduct(String fieldToUpdate) {
        UpdateProductModel updateProductModel = new UpdateProductModel();
        UpdateProductView updateProductView = new UpdateProductView();
        try {
            new UpdateProductController(updateProductModel,updateProductView,fieldToUpdate);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
