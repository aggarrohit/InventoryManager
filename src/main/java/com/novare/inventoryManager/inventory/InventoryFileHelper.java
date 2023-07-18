package com.novare.inventoryManager.inventory;

import com.novare.inventoryManager.notification.NotificationsImpl;
import com.novare.inventoryManager.product.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import static com.novare.inventoryManager.inventory.Constants.PATH_TO_ASSETS;

public class InventoryFileHelper {

    private static final String PATH_TO_INVENTORY_FILE = PATH_TO_ASSETS + "/inventory.txt";

    private InventoryFileHelper() {
    }


    public static List<Product> getProducts() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(PATH_TO_INVENTORY_FILE));
        List<Product> products = new ArrayList<>();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if(!nextLine.isEmpty()) {
                Product product = convertStringToProduct(nextLine);
                products.add(product);
            }
        }
        sc.close();
        return products;
    }

    public static void addProduct(Product product) throws IllegalArgumentException {
        try {
            if (isProductPresent(product.product_name())) throw new IllegalArgumentException("Product already present!");

            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_INVENTORY_FILE, true));
            writer.write(product+System.lineSeparator());

            //remove this
            checkInventoryThreshold(product);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean isProductPresent(String productName) {

        List<Product> products;

        try {
            products = getProducts();
            long productsWithSameName =  products.stream()
                    .filter(product->product.product_name().equals(productName))
                    .count();

            if(productsWithSameName!=0) return true;

        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }

        return false;
    }

    public static List<Product> getProductsByName(String searchName) throws FileNotFoundException {

        return getProducts().stream()
                .filter(product -> product.product_name()
                        .toLowerCase()
                        .contains(searchName)
                )
                .toList();

    }

    public static synchronized void updateQuantity(UUID productId,BigDecimal updatedQty) throws NoSuchElementException {

        Product product = getProductByProductId(productId);
        if(product==null) throw new NoSuchElementException("Product not found");

        Product updatedProduct = replaceQuantityInProduct(product,updatedQty);
        updateProduct(updatedProduct);

        checkInventoryThreshold(updatedProduct);

    }

    public static void updateThresholdQuantity(UUID productId,BigDecimal updatedThresholdQty) throws NoSuchElementException {

        Product product = getProductByProductId(productId);
        if(product==null) throw new NoSuchElementException("Product not found");

        Product updatedProduct = replaceThresholdQuantityInProduct(product,updatedThresholdQty);
        updateProduct(updatedProduct);
        checkInventoryThreshold(updatedProduct);

    }

    public static synchronized void updateProduct(Product updatedProduct) {

        try {
            // Read the original file
            File inputFile = new File(PATH_TO_INVENTORY_FILE);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Store the modified content in a list
            List<String> modifiedContent = new ArrayList<>();

            // Replace the desired entry
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith(updatedProduct.id().toString())) {
                    modifiedContent.add(updatedProduct.toString());
                } else {
                    modifiedContent.add(currentLine);
                }
            }

            // Close the resources
            reader.close();

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            for (String line : modifiedContent) {
                writer.write(line);
                writer.newLine();
            }

            // Close the writer
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized BigDecimal getQtyByProductId(UUID productId) throws IllegalArgumentException{

        Product product = getProductByProductId(productId);
        if(product==null) throw new IllegalArgumentException("Product not found!!");
        return product.quantity();

    }

    public static void checkInventoryThreshold(Product product){
        int productQuantityCompareToThresholdQuantity = product.quantity().compareTo(product.threshold_qty()); 
        if(productQuantityCompareToThresholdQuantity <= 0) addProductNotification(product);
    }

    private static void addProductNotification(Product product) {
        NotificationsImpl notificationsImpl = new NotificationsImpl();
        notificationsImpl.createNotification(product.product_name()+" threshold quantity reached.",product.id());
        notificationsImpl.printNotifications(notificationsImpl.getNotifications());
    }

    private static Product getProductByProductId(UUID productId) throws IndexOutOfBoundsException{

        try {
            List<Product> productsWithSameId =  getProducts().stream()
                    .filter(product->product.id().equals(productId))
                    .toList();

            if(productsWithSameId.size()==0) throw new IndexOutOfBoundsException();

            return productsWithSameId.get(0);

        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }

        return null;
    }

    private static Product replaceQuantityInProduct(Product product,BigDecimal updatedQty){
        return new Product( product.id(),
                product.product_name(),
                product.measurement(),
                updatedQty,
                product.threshold_qty(),
                product.price()
        );
    }

    private static Product replaceThresholdQuantityInProduct(Product product,BigDecimal updatedThresholdQty){
        return new Product( product.id(),
                product.product_name(),
                product.measurement(),
                product.quantity(),
                updatedThresholdQty,
                product.price()
        );
    }

    private static Product convertStringToProduct(String productString) {
        String[] productFields = productString.split(",");
        return new Product(  UUID.fromString(productFields[0]),
                productFields[1],
                Measurement.getMeasurementByValue(productFields[2]),
                new BigDecimal(productFields[3]),
                new BigDecimal(productFields[4]),
                new BigDecimal(productFields[5])
        );
    }

}
