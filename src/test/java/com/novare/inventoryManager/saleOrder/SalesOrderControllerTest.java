package com.novare.inventoryManager.saleOrder;

import com.novare.inventoryManager.data.order.SalesOrder;
import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.purchaseOrder.InventoryDummyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SalesOrderControllerTest {

    @Mock
    private SalesOrderModel mockModel;
    @Mock
    private SalesOrderView mockView;
    private SalesOrderController controller;
    private List<Product> inventory;

    @BeforeEach
    public void setUp() {
        // Create mock objects
        MockitoAnnotations.openMocks(this);
        inventory = new ArrayList<>();
        controller = new SalesOrderController(mockModel, mockView);
    }

    @Test
    public void testCreateSalesOrder_AddSingleItem() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
                Sales order details
                Enter the item number:\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createSalesOrder();

        // Verify the results
        verify(mockModel).addSalesOrderToOrderInventory(Mockito.anyList());
        verify(mockModel).updateProductQuantityById(Mockito.any(), Mockito.any());
        verify(mockView).displaySalesOrder(Mockito.anyList());
        verify(mockView, times(2)).displayInventory(Mockito.anyList());

        // Assertion
        ArgumentCaptor<List<SalesOrder>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockModel).addSalesOrderToOrderInventory(captor.capture());
        List<SalesOrder> salesOrders = captor.getValue();
        assertEquals(1, salesOrders.size()); // Check if a single sales order is created

        SalesOrder salesOrder = salesOrders.get(0);
        assertEquals(product, salesOrder.getProduct()); // Check if the selected product is correct
        assertEquals(BigDecimal.valueOf(5), salesOrder.getOrderQuantity()); // Check if the quantity is correct
        assertEquals("Customer ABC", salesOrder.getCustomerName()); // Check if the customer name is correct
        assertEquals(product.price(), salesOrder.getPrice()); // Check if the price is correct
    }
    @Test
    public void testCreateSalesOrder_AddMultipleItems() throws FileNotFoundException {
        // Set up test data
        Product product1 = InventoryDummyData.getProducts().get(0);
        Product product2 = InventoryDummyData.getProducts().get(1);
        inventory.add(product1);
        inventory.add(product2);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
            Sales order details
            Enter the item number:\s""")).thenReturn(1, 2); // Select item numbers 1 and 2
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.valueOf(5), BigDecimal.valueOf(3)); // Set quantities
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(true, false); // Add more items: Yes, No

        // Execute the test case
        controller.createSalesOrder();

        // Verify the results
        verify(mockModel).addSalesOrderToOrderInventory(Mockito.anyList());
        verify(mockModel, times(2)).updateProductQuantityById(Mockito.any(), Mockito.any());
        verify(mockView).displaySalesOrder(Mockito.anyList());
        verify(mockView, times(2)).displayInventory(Mockito.anyList());

        // Assertion
        ArgumentCaptor<List<SalesOrder>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockModel).addSalesOrderToOrderInventory(captor.capture());
        List<SalesOrder> salesOrders = captor.getValue();
        assertEquals(2, salesOrders.size()); // Check if two sales orders are created

        SalesOrder salesOrder1 = salesOrders.get(0);
        assertEquals(product1, salesOrder1.getProduct()); // Check if the selected product for item 1 is correct
        assertEquals(BigDecimal.valueOf(5), salesOrder1.getOrderQuantity()); // Check if the quantity for item 1 is correct

        SalesOrder salesOrder2 = salesOrders.get(1);
        assertEquals(product2, salesOrder2.getProduct()); // Check if the selected product for item 2 is correct
        assertEquals(BigDecimal.valueOf(3), salesOrder2.getOrderQuantity()); // Check if the quantity for item 2 is correct
    }
    @Test
    public void testCreateSalesOrder_InvalidItemNumber() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
                Sales order details
                Enter the item number:\s""")).thenReturn(0, 2, 1); // Select invalid item number
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createSalesOrder();

        // Verify the results
        verify(mockView, times(2)).displayErrorMessage("Enter a valid number between 1 and " + inventory.size());
    }

    @Test
    public void testCreateSalesOrder_InvalidQuantity() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
                Sales order details
                Enter the item number:\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.ZERO, BigDecimal.valueOf(5)); // Set invalid quantity
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createSalesOrder();

        // Verify the results
        verify(mockView).displayErrorMessage("Enter a valid quantity.");
    }

    @Test
    public void testCreateSalesOrder_InsufficientQuantity() throws FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
                Sales order details
                Enter the item number:\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.valueOf(20),BigDecimal.valueOf(10)); // Set quantity as 10
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(false); // Add more items: No

        // Execute the test case
        controller.createSalesOrder();

        // Verify the results
        verify(mockView).displayErrorMessage("Insufficient quantity. Available: " + product.quantity());
    }
    @Test
    public void testCreateSalesOrder_ConcurrentProductUpdates() throws InterruptedException, FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs
        when(mockView.getInput("Enter the customer name: ")).thenReturn("Customer ABC"); // Set customer name
        when(mockView.getIntNumericUserInput("""
                Sales order details
                Enter the item number:\s""")).thenReturn(1); // Select item number 1
        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: ")).thenReturn(BigDecimal.valueOf(5)); // Set quantity as 5
        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): ")).thenReturn(false);

        // Number of threads to execute concurrently
        int numThreads = 10;

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // Submit the tasks to the executor
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    controller.createSalesOrder();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Shutdown the executor and wait for all tasks to complete
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        // Verify the results
        verify(mockModel, times(numThreads)).addSalesOrderToOrderInventory(Mockito.anyList());
        verify(mockModel, times(numThreads)).updateProductQuantityById(Mockito.any(UUID.class), Mockito.any(BigDecimal.class));
    }
    @Test
    public void testCreateSalesOrder_ConcurrentProductUpdates_TwoCustomersBuyTen() throws InterruptedException, FileNotFoundException {
        // Set up test data
        Product product = InventoryDummyData.getProducts().get(0);
        inventory.add(product);
        when(mockModel.getInventoryProducts()).thenReturn(inventory);

        // Set up user inputs for both customers
        String customerName1 = "Customer ABC";
        String customerName2 = "Customer XYZ";
        when(mockView.getInput("Enter the customer name: "))
                .thenReturn(customerName1, customerName2);

        when(mockView.getIntNumericUserInput("""
        Sales order details
        Enter the item number:\s""")).thenReturn(1); // Select item number 1

        when(mockView.getBigDecimalNumericUserInput("Enter the quantity: "))
                .thenReturn(BigDecimal.valueOf(10)); // Set quantity as 10 for both customers

        when(mockView.getYesNoUserInput("Do you want to add more items? (Y/N): "))
                .thenReturn(false); // Add more items: No

        // Number of threads to execute concurrently
        int numThreads = 2;

        // Create a lock to synchronize access to the critical section (inventory update)
        Lock inventoryLock = new ReentrantLock();

        // Create a list to hold the threads
        List<Thread> threads = new LinkedList<>();

        // Create and start the threads
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                // Acquire the lock before entering the critical section
                try {
                    controller.createSalesOrder();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Verify the results
        verify(mockModel, times(numThreads)).addSalesOrderToOrderInventory(Mockito.anyList());
        verify(mockModel, times(numThreads)).updateProductQuantityById(Mockito.any(UUID.class), Mockito.any(BigDecimal.class));

        // Assertion: Verify that only one sales order is created, as the quantity is not sufficient for both orders
        ArgumentCaptor<List<SalesOrder>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockModel, times(numThreads)).addSalesOrderToOrderInventory(captor.capture());
        List<SalesOrder> salesOrders = captor.getValue();
        assertEquals(1, salesOrders.size());

        SalesOrder salesOrder1 = salesOrders.get(0);
        assertEquals(BigDecimal.valueOf(10), salesOrder1.getOrderQuantity());
    }
}