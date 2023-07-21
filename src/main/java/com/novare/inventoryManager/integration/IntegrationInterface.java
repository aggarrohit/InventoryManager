package com.novare.inventoryManager.integration;



import java.util.List;

public interface IntegrationInterface {
    void createIntegrateFile();

    List<IntegrationFile> getIntegrateFile();

}
