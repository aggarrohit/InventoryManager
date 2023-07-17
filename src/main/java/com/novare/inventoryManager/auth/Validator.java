package com.novare.inventoryManager.auth;

import java.security.NoSuchAlgorithmException;

public class Validator {
    private Validator(){}

    public static boolean validateSocialNumber(String socialNumber) {
        String SOCIAL_NUMBER_PATTERN = "\\d{4}-[a-zA-Z]{3}";
        return socialNumber.matches(SOCIAL_NUMBER_PATTERN);
    }

    public static boolean validatePassword(String storedPassword, String inputPassword) throws NoSuchAlgorithmException {
        String hashedPassword = PasswordHasher.hashPassword(inputPassword);
        return storedPassword.equals(hashedPassword);
    }

}