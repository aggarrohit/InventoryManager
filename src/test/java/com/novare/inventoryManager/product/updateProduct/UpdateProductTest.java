package com.novare.inventoryManager.product.updateProduct;

import com.novare.inventoryManager.product.InventoryTestData;
import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static com.novare.inventoryManager.inventory.Constants.PRODUCT_PRICE;
import static com.novare.inventoryManager.inventory.Constants.PRODUCT_THRESHOLD_QUANTITY;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class UpdateProductTest {

    @Mock
    private UpdateProductView mockView;
    @Mock
    private UpdateProductModel mockModel;

    private List<Product> inventory;

    @BeforeEach
    public void populateDataAndInit(){
        MockitoAnnotations.openMocks(this);
        inventory = InventoryTestData.getProducts();
    }

    @Test
    public void testForUpdateProduct_Price() throws FileNotFoundException {


        BigDecimal itemNumberToUpdate = BigDecimal.valueOf(2);
        BigDecimal itemUpdatedPrice = BigDecimal.valueOf(4);

        when(mockModel.getProducts()).thenReturn(inventory);
        when(mockModel.getNumberInput()).thenReturn(itemNumberToUpdate,itemUpdatedPrice);

        Product product = inventory.get(itemNumberToUpdate.intValue()-1);
        Product updatedProduct = new Product(product.id(),product.product_name(),product.measurement(),
                product.quantity(),product.threshold_qty(),itemUpdatedPrice);

        doAnswer(invocationOnMock -> {

            inventory.set(itemNumberToUpdate.intValue()-1,updatedProduct);
            return null;
        }).when(mockModel).updatePrice(updatedProduct.id(),itemUpdatedPrice);

        new UpdateProductController(mockModel, mockView, PRODUCT_PRICE);

        Assertions.assertEquals(itemUpdatedPrice,inventory.get(itemNumberToUpdate.intValue()-1).price());

    }


    @Test
    public void testForUpdateProduct_ThresholdQuantity() throws FileNotFoundException {


        BigDecimal itemNumberToUpdate = BigDecimal.valueOf(2);
        BigDecimal itemUpdatedThresholdQuantity = BigDecimal.valueOf(8);

        when(mockModel.getProducts()).thenReturn(inventory);
        when(mockModel.getNumberInput()).thenReturn(itemNumberToUpdate,itemUpdatedThresholdQuantity);

        Product product = inventory.get(itemNumberToUpdate.intValue()-1);
        Product updatedProduct = new Product(product.id(),product.product_name(),product.measurement(),
                product.quantity(),itemUpdatedThresholdQuantity,product.price());

        doAnswer(invocationOnMock -> {
            inventory.set(itemNumberToUpdate.intValue()-1,updatedProduct);
            return null;
        }).when(mockModel).updateThresholdQuantity(updatedProduct.id(),itemUpdatedThresholdQuantity);

        new UpdateProductController(mockModel, mockView, PRODUCT_THRESHOLD_QUANTITY);

        Assertions.assertEquals(itemUpdatedThresholdQuantity,inventory.get(itemNumberToUpdate.intValue()-1).threshold_qty());

    }

}