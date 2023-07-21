package com.novare.inventoryManager.auth;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public class AuthenticatorTest {

    @Test
    void testLogInSuccess() throws NoSuchAlgorithmException {
        String password = "MyPassword123";
        String hashedPassword = PasswordHasher.hashPassword(password);

        Employee employee = new Employee("John Smith", "2000-abc", new BigDecimal("20000"), EmployeeRole.
                CASHIER);
        employee.setPassword(hashedPassword);
        boolean result = Authenticator.logIn(employee, password);

        Assertions.assertTrue(result);
    }

    @Test
    void testLogInFailure() throws NoSuchAlgorithmException {
        String correctPassword = "MyPassword123";
        String wrongPassword = "wrongPassword";
        String hashedPassword = PasswordHasher.hashPassword(correctPassword);

        Employee employee = new Employee("John Smith", "2000-abc", new BigDecimal("20000"), EmployeeRole.
                CASHIER);
        employee.setPassword(hashedPassword);
        boolean result = Authenticator.logIn(employee, wrongPassword);

        Assertions.assertFalse(result);
    }
}
