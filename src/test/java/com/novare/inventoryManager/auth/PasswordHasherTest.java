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

        String expectedHashedPassword = "bc7b8851671f2fda237a53f5057a0376037b6d062e65f965c62aa1d047498759";
        Assertions.assertEquals(expectedHashedPassword, hashedPassword);
    }

}

