package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public class Inventory {

    private static volatile boolean isProductQuantitiesSufficient = true;

    private Inventory() {
    }

    public static boolean doesProductExist(String productName) {
        return InventoryFileHelper.isProductPresent(productName);
    }

    public static void addProductToInventory(Product product){
        InventoryFileHelper.addProduct(product);
    }

    public static void listInventory(){
        try {
            printTable(InventoryFileHelper.getProducts());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkAndUpdateOrderQuantities(List<Product> orderProducts) throws InterruptedException {

        isProductQuantitiesSufficient = true;


        Thread thread = new Thread( ()-> {
                for(Product product:orderProducts){
                    //checking if any product has lesser quantity than required for creating order
                    int availableQtyCompareToOrderQty = InventoryFileHelper.getQtyByProductId(product.id()).compareTo(product.quantity());
                    if(availableQtyCompareToOrderQty<0) isProductQuantitiesSufficient=false;
                }


                if(isProductQuantitiesSufficient) {
                    for(Product product:orderProducts){

                        //reducing product quantity in inventory
                        BigDecimal updatedQuantity = InventoryFileHelper.getQtyByProductId(product.id()).subtract(product.quantity());
                        InventoryFileHelper.updateQuantity(product.id(),updatedQuantity);
                    }
                }

            }
        );

        thread.start();
        thread.join();


        return isProductQuantitiesSufficient;
    }

    public static void printTable(List<Product> inventory) {

        printHeader();
        String transactionsTableFormat = "%-7s %-25s %-12s %-12s %-15s %-15s%n";
        System.out.printf(transactionsTableFormat, "Sr.No.","Name", "Qty.", "Unit", "Price", "Threshold Qty.");
        printSeparatorLine();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            System.out.printf(transactionsTableFormat,
                    i+1,
                    product.product_name(),
                    product.quantity(),
                    product.measurement(),
                    product.price(),
                    product.threshold_qty()
                    );
        }
        printSeparatorLine();
    }

    private static void printSeparatorLine(){
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private static void printHeader(){
        printSeparatorLine();
        System.out.println("*************************************** Inventory ***************************************");
        printSeparatorLine();
    }
}
