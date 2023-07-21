package com.novare.inventoryManager.data.order;

import com.novare.inventoryManager.product.Product;

import java.math.BigDecimal;
import java.util.*;


public class SalesOrders {
    private static List<SalesOrder> salesOrders;
    private final UUID orderId;

    public SalesOrders(List<SalesOrder> salesOrders) {
        this.orderId=UUID.randomUUID();
        SalesOrders.salesOrders = salesOrders;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<SalesOrder> getSaleOrders() {
        return salesOrders;
    }

    public void addOrder(SalesOrder salesOrder) {
        salesOrders.add(salesOrder);
    }

    public List<SalesStatistics> getMostSoldItemsStatistics(List<SalesOrder> salesOrders) {
       // List<SalesOrder> salesOrders = getSaleOrders();

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
