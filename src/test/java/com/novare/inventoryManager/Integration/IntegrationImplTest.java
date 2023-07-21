package com.novare.inventoryManager.Integration;

import com.novare.inventoryManager.integration.IntegrationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationImplTest {

    private IntegrationImpl integration;

    @BeforeEach
    void setUp() {
        integration = new IntegrationImpl();
    }

    @Test
    void createIntegrateFile_FileCreated() {
        integration.createIntegrateFile();

        // Check if the file was created successfully
        File exportFile = new File(integration.Export_File_PATH);
        assertTrue(exportFile.exists());
    }
}