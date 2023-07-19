package com.novare.inventoryManager.product;

import java.math.BigDecimal;

public class AddProductModel {

    private String enteredText;

    public BigDecimal getEnteredNumber() {
        return enteredNumber;
    }

    public void setEnteredNumber(BigDecimal enteredNumber) {
        this.enteredNumber = enteredNumber;
    }

    private BigDecimal enteredNumber;
    public String getEnteredText() {
        return enteredText;
    }

    public void setEnteredText(String enteredText) {
        this.enteredText = enteredText;
    }


}
