package com.novare.inventoryManager.auth;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Validator {
    private Validator(){}

    public static boolean validateSocialNumber(String socialNumber) {
        String SOCIAL_NUMBER_PATTERN = "\\d{4}-[a-zA-Z]{3}";
        return socialNumber.matches(SOCIAL_NUMBER_PATTERN);
    }

    public static boolean validateStoredPassword(String storedPassword, String inputPassword) throws NoSuchAlgorithmException {
        String hashedPassword = PasswordHasher.hashPassword(inputPassword);
        return storedPassword.equals(hashedPassword);
    }

    public static boolean validatePasswordFormat(String inputPassword) throws IllegalArgumentException {
        // The password should contain at least 1 uppercase letter, at least 1 lowercase letter, at least 1 digit, at least 8 characters in total
        Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
        boolean isValid = PASSWORD_PATTERN.matcher(inputPassword).matches();

        if(!isValid) {
            throw new IllegalArgumentException("Invalid password format. It should contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and be at least 8 characters long.");
        }

        return isValid;
    }
}