package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.product.Product;
import com.novare.inventoryManager.utils.Table;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
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

        List<Integer> columnWidths = List.of(7, 25, 12, 12, 15, 15);
        List<String> headers = List.of("Sr.No.","Name", "Qty.", "Unit", "Price", "Threshold Qty.");
        List<List<String>> body = parseInventory(inventory);
        Table table = new Table(columnWidths, headers, body,"Inventory");

        table.showData();

    }

    private static List<List<String>> parseInventory(List<Product> inventory) {
        List<List<String>> result = new ArrayList<>();

        int sr_no = 0;
        for (Product product: inventory) {
            sr_no++;
            String srNo = String.valueOf(sr_no);
            String name = product.product_name();
            String quantity = product.quantity().toString();
            String measurement = product.measurement().getMeasurement();
            String price = product.price().toString();
            String threshold_qty = product.threshold_qty().toString();
            List<String> data = List.of(srNo,name,quantity,measurement,price,threshold_qty);

            result.add(data);
        }

        return result;
    }


}
