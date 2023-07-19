package com.novare.inventoryManager.inventory;

import java.util.List;

public enum Measurement {
    KILOGRAMS("kg"),
    LITRES("l"),
    PIECES("pc");

    private final String measurement;

    Measurement(String measurement) {
        this.measurement=measurement;
    }

    public String getMeasurement() {
        return measurement;
    }

    public static List<String> getValues(){

        return List.of(
                KILOGRAMS.getMeasurement(),
                LITRES.getMeasurement(),
                PIECES.getMeasurement()
        );
    }

    public static Measurement getMeasurementByValue(String value) {
        for (Measurement measure : Measurement.values()) {
            if (measure.measurement.equals(value)) {
                return measure;
            }
        }
        return null; // Value not found
    }

}
