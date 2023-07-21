package com.novare.inventoryManager.purchaseOrder;

import com.novare.inventoryManager.order.PurchaseOrder;
import com.novare.inventoryManager.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PurchaseOrderControllerTest {

    @Mock
    private PurchaseOrderModel mockModel;
    @Mock
    private PurchaseOrderView mockView;
    private PurchaseOrderController controller;
    private List<Product> inventory;

    @BeforeEach
    public void setUp() {
        // Create mock objects
        MockitoAnnotations.openMocks(this);
        inventory = new ArrayList<>();
        controller = new PurchaseOrderController(mockModel, mockView);
    }


    @Test
    public void testCreatePurchaseOrder_AddSingleItem() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.valueOf(8)); // Set price as 8
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createPurchaseOrder();

        // Verify the results
        verify(mockModel).addPurchaseOrderToOrderInventory(Mockito.anyList());
        verify(mockModel).updateProductQuantityById(Mockito.any(UUID.class), Mockito.any(BigDecimal.class));
        verify(mockView, times(1)).displayInventory(inventory);

        // Assertion
        ArgumentCaptor<List<PurchaseOrder>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockModel).addPurchaseOrderToOrderInventory(captor.capture());
        List<PurchaseOrder> purchaseOrders = captor.getValue();
        assertEquals(1, purchaseOrders.size()); // Check if a single purchase order is created

        PurchaseOrder purchaseOrder = purchaseOrders.get(0);
        assertEquals(product, purchaseOrder.getProduct()); // Check if the selected product is correct
        assertEquals(BigDecimal.valueOf(5), purchaseOrder.getOrderQuantity()); // Check if the quantity is correct
        assertEquals("Company XYZ", purchaseOrder.getCompanyName()); // Check if the company name is correct
        assertEquals(BigDecimal.valueOf(8), purchaseOrder.getPrice()); // Check if the price is correct
    }

    @Test
    public void testCreatePurchaseOrder_AddMultipleItems() throws FileNotFoundException {
        // Set up test data
        Product product1 = InventoryDummyData.getProducts().get(0);
        Product product2 = InventoryDummyData.getProducts().get(1);
        inventory.add(product1);
        inventory.add(product2);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(1, 2); // Select item numbers 1 and 2
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.valueOf(5), BigDecimal.valueOf(3)); // Set quantities
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.valueOf(8),BigDecimal.valueOf(10)); // Set price for item 1
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(true, false); // Add more items: Yes, No

        // Execute the test case
        controller.createPurchaseOrder();

        // Verify the results
        verify(mockModel).addPurchaseOrderToOrderInventory(Mockito.anyList());
        verify(mockModel, times(2)).updateProductQuantityById(Mockito.any(UUID.class), Mockito.any(BigDecimal.class));
        verify(mockView, times(1)).displayInventory(inventory);

        // Assertion
        ArgumentCaptor<List<PurchaseOrder>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockModel).addPurchaseOrderToOrderInventory(captor.capture());
        List<PurchaseOrder> purchaseOrders = captor.getValue();
        assertEquals(2, purchaseOrders.size()); // Check if two purchase orders are created

        PurchaseOrder purchaseOrder1 = purchaseOrders.get(0);
        assertEquals(product1, purchaseOrder1.getProduct()); // Check if the selected product for item 1 is correct
        assertEquals(BigDecimal.valueOf(5), purchaseOrder1.getOrderQuantity()); // Check if the quantity for item 1 is correct

        PurchaseOrder purchaseOrder2 = purchaseOrders.get(1);
        assertEquals(product2, purchaseOrder2.getProduct()); // Check if the selected product for item 2 is correct
        assertEquals(BigDecimal.valueOf(3), purchaseOrder2.getOrderQuantity()); // Check if the quantity for item 2 is correct
    }
    @Test
    public void testCreatePurchaseOrder_InvalidItemNumber() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(0,2,1); // Select invalid item number
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.valueOf(8)); // Set price as 8
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createPurchaseOrder();

        // Verify the results
        verify(mockView, times(2)).displayErrorMessage("Enter a valid number between 1 and " + inventory.size());

    }

    @Test
    public void testCreatePurchaseOrder_InvalidQuantity() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.ZERO,BigDecimal.valueOf(5)); // Set invalid quantity
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.valueOf(8)); // Set price as 8
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createPurchaseOrder();

        // Verify the results
        verify(mockView).displayErrorMessage("Enter a valid quantity.");
    }

    @Test
    public void testCreatePurchaseOrder_InvalidPrice() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.ONE); // Set valid quantity
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.ZERO,BigDecimal.valueOf(8)); // Set invalid price
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createPurchaseOrder();

        // Verify the results
        verify(mockView).displayErrorMessage("Enter a valid price.");
    }
    @Test
    public void testCreatePurchaseOrder_ConcurrentProductUpdates() throws InterruptedException, FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getIntNumericUserInput("""
                
                Purchase order details
                Enter the item number\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getInput("Enter the Company name")).thenReturn("Company XYZ"); // Set company name
        when(mockView.getBigDecimalNumericUserInput("Enter the purchase price")).thenReturn(BigDecimal.valueOf(8)); // Set price as 8
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N)")).thenReturn(false); // Add more items: No

        // Number of threads to execute concurrently
        int numThreads = 10;

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // Submit the tasks to the executor
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    controller.createPurchaseOrder();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Shutdown the executor and wait for all tasks to complete
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        // Verify the results
        verify(mockModel, times(numThreads)).addPurchaseOrderToOrderInventory(Mockito.anyList());
        verify(mockModel, times(numThreads)).updateProductQuantityById(Mockito.any(UUID.class), Mockito.any(BigDecimal.class));
    }
}