package com.novare.inventoryManager.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novare.inventoryManager.inventory.InventoryFileHelper;
import com.novare.inventoryManager.product.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class SalesOrderInventory {
    private static final String Sales_Order_PATH = System.getProperty("user.dir") + "/data/SalesOrder.txt";
    private final List<SalesOrder> salesOrders = Collections.synchronizedList(loadSalesOrdersFromFileTxt());

    public List<SalesOrder> getSaleOrders() {
        return salesOrders;
    }

    public List<SalesOrder> getSaleOrderById(UUID requestedId) throws NoSuchElementException {
        List<SalesOrder> result = new ArrayList<>();
        for (SalesOrder saleList : salesOrders) {
            if (saleList.getOrderId().equals(requestedId)) {
                result.add(saleList);
            }
        }
        return result;
    }

    public SalesOrder getSaleOrderByIndex(int index) {
        return salesOrders.get(index);
    }

    public synchronized void addSaleOrder(List<SalesOrder> saleOrder) {
        for (SalesOrder salesOrder1 : saleOrder) {
            salesOrders.add(salesOrder1);
        }
        saveSalesOrdersToFileTxt(saleOrder);
    }

    public int SaleOrderInventoryCount() {
        return salesOrders.size();
    }

    public List<SalesOrder> loadSalesOrdersFromFileTxt() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Sales_Order_PATH));
            List<SalesOrder> listOfSalesOrders = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (!nextLine.isEmpty()) {
                    SalesOrder salesOrder = convertStringToSalesOrders(nextLine);
                    listOfSalesOrders.add(salesOrder);
                }
            }
            sc.close();
            return listOfSalesOrders;
        } catch (FileNotFoundException e) {
            System.out.println("SalesOrder Inventory file not found.");
        }
        return new ArrayList<>();
    }

    private SalesOrder convertStringToSalesOrders(String salesOrderString) {
        String[] salesOrder = salesOrderString.split(",");
        return new SalesOrder(UUID.fromString(salesOrder[0]),
                InventoryFileHelper.getProductByProductId(UUID.fromString(salesOrder[1])),
                new BigDecimal(salesOrder[3]),
                salesOrder[4],
                salesOrder[5],
                new BigDecimal(salesOrder[6])
        );
    }

    public void saveSalesOrdersToFileTxt(List<SalesOrder> salesOrders) throws IllegalArgumentException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Sales_Order_PATH, true));
            for (SalesOrder salesOrder : salesOrders) {
                String line = salesOrderToString(salesOrder);
                writer.write(line + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private String salesOrderToString(SalesOrder salesOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(salesOrder.getOrderId().toString()).append(",");
        sb.append(salesOrder.getProduct().id().toString()).append(",");
        sb.append(salesOrder.getProduct().measurement().toString()).append(",");
        sb.append(salesOrder.getOrderQuantity()).append(",");
        sb.append(salesOrder.getDate()).append(",");
        sb.append(salesOrder.getCustomerName()).append(",");
        sb.append(salesOrder.getPrice());
        return sb.toString();
    }

    public List<SalesStatistics> getMostSoldItemsStatistics() {
        List<SalesOrder> salesOrders = getSaleOrders();

        Map<Product, BigDecimal> totalQuantityMap = new HashMap<>();
        Map<Product, BigDecimal> totalPriceMap = new HashMap<>();

        try {

            for(SalesOrder order : salesOrders) {
                Product product = order.getProduct();
                BigDecimal orderQuantity = order.getOrderQuantity();
                BigDecimal orderPrice = order.getPrice();

                BigDecimal totalQuantity = totalQuantityMap.getOrDefault(product, BigDecimal.ZERO);
                totalQuantity = totalQuantity.add(orderQuantity);
                totalQuantityMap.put(product, totalQuantity);

                BigDecimal totalPrice = totalPriceMap.getOrDefault(product, BigDecimal.ZERO);
                totalPrice = totalPrice.add(orderQuantity.multiply(orderPrice));
                totalPriceMap.put(product, totalPrice);
            }

            List<SalesStatistics> result = new ArrayList<>();

            List<Map.Entry<Product, BigDecimal>> sortedProducts = new ArrayList<>(totalQuantityMap.entrySet());
            sortedProducts.sort(Map.Entry.<Product, BigDecimal>comparingByValue().reversed());

            for(Map.Entry<Product, BigDecimal> entry : sortedProducts) {
                Product product = entry.getKey();
                BigDecimal totalQuantity = entry.getValue();
                BigDecimal totalPrice = totalPriceMap.get(product);
                BigDecimal averagePrice = totalPrice.divide(totalQuantity, 2);
                result.add(new SalesStatistics(product, totalQuantity, averagePrice));
            }
            return result;
            }

        catch(ArithmeticException exception) {
            System.out.println(exception.getMessage());
            return null;
        }

    }
}
