package com.novare.inventoryManager.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    @Test
    void testValidateSocialNumber_ValidFormat() {
        String validSocialNumber = "2000-abc";

        boolean result = Validator.validateSocialNumber(validSocialNumber);

        Assertions.assertTrue(result);
    }

    @Test
    void validateSocialNumberInvalidFormat() {
        String invalidSocialNumber = "200000-abcd";

        boolean result = Validator.validateSocialNumber(invalidSocialNumber);

        Assertions.assertFalse(result);
    }

    @Test
    void testValidatePasswordFormat_ValidFormat() {
        String validPassword = "SecurePassw0rd";

        boolean result = Validator.validatePasswordFormat(validPassword);

        Assertions.assertTrue(result);
    }

    @Test
    void testValidatePasswordFormat_InvalidFormat() {
        String invalidPassword = "invalid";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Validator.validatePasswordFormat(invalidPassword));
    }
}
