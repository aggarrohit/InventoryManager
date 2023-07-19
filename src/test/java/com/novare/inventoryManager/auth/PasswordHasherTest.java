package com.novare.inventoryManager.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class PasswordHasherTest {
    @Test
    void testHashPassword() throws NoSuchAlgorithmException {
        String password = "MyPassword123";
        String hashedPassword = PasswordHasher.hashPassword(password);

        Assertions.assertFalse(hashedPassword.isEmpty());

        String expectedHashedPassword = "7984d027b3ce81e0a0ab60d431b28b32b0f8c0491105daaeb54d4df00d80f4a1";
        Assertions.assertEquals(expectedHashedPassword, hashedPassword);
    }

    @Test
    void testHashNullPassword() {
        String password = null;

        Assertions.assertThrows(NoSuchAlgorithmException.class, () -> {
            PasswordHasher.hashPassword(password);
        });
    }

}

