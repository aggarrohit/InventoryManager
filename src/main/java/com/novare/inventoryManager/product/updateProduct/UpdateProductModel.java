package com.novare.inventoryManager.product.updateProduct;

import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.product.Product;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UpdateProductModel implements IUpdateProduct{

    public BigDecimal getNumberInput() throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {

            if(new BigDecimal(input.trim()).compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
            return new BigDecimal(input.trim());

        }catch (NumberFormatException exception){
            throw new NumberFormatException();
        }
    }

    public List<Product> getProducts() throws FileNotFoundException {
        return InventoryFileHelper.getProducts();
    }

    @Override
    public void updateThresholdQuantity(UUID productId,BigDecimal thresholdQty){
        InventoryFileHelper.updateThresholdQuantity(productId, thresholdQty);
    }

    @Override
    public void updatePrice(UUID productId,BigDecimal price){
        InventoryFileHelper.updatePrice(productId, price);
    }
}
