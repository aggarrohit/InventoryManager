package com.novare.inventoryManager.product;

public class AddProduct {
    public AddProduct() {
        AddProductModel addProductModel = new AddProductModel();
        AddProductView addProductView = new AddProductView();
        new AddProductController(addProductModel,addProductView);
    }
}
