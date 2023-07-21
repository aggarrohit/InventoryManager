package com.novare.inventoryManager.product.addProduct;

import com.novare.inventoryManager.inventory.Measurement;
import com.novare.inventoryManager.product.InventoryTestData;
import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class AddProductControllerTest {

    @Mock
    private AddProductView mockView;
    @Mock
    private AddProductModel mockModel;

    private List<Product> inventory;

    @BeforeEach
    public void populateDataAndInit(){
        MockitoAnnotations.openMocks(this);
        inventory = InventoryTestData.getProducts();
    }

    @Test
    public void testForAddProduct_Success() {

        String productNameInput = "Maple Syrup";
        String measurementInput = "kg";
        BigDecimal thresholdQuantity = BigDecimal.valueOf(4);
        BigDecimal price = BigDecimal.valueOf(10);

        Product product = new Product(  UUID.randomUUID(),
                productNameInput,
                Measurement.getMeasurementByValue(measurementInput),
                BigDecimal.ZERO,
                thresholdQuantity,
                price
        );


        inventory.add(product);
        doAnswer(invocationOnMock -> {
            inventory.add(product);
            return null;
        }).when(mockModel).addProductToInventory(product);

        when(mockModel.getTextInput()).thenReturn(productNameInput,measurementInput);
        when(mockModel.getNumberInput()).thenReturn(thresholdQuantity,price);

        new AddProductController(mockModel, mockView);

        Assertions.assertEquals(product,inventory.get(inventory.size()-1));

    }



}