package com.novare.inventoryManager.product;

import java.util.ArrayList;
import java.util.List;

public class ProductHelper {

    public static int countSpecialCharacters(String input) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                count++;
            }
        }

        return count;
    }
    public static List<String> getProductNameShortcomings(String productName){

        List<String>productNameShortComings = new ArrayList<>();

        if(productName.length()>25) productNameShortComings.add("Product name can be maximum 25 letters.");
        if(countSpecialCharacters(productName)!=0) productNameShortComings.add("Product name can not have any special character.");

        for (String issue:productNameShortComings){
            System.out.println(issue);
        }

        return productNameShortComings;
    }
}
