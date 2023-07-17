package com.novare.inventoryManager.auth;

import com.novare.inventoryManager.employees.Employee;

import java.security.NoSuchAlgorithmException;

public class Authenticator {
    private Authenticator() {}
    public static boolean logIn(Employee employee, String password) throws NoSuchAlgorithmException {
        return Validator.validatePassword(employee.getPassword(), password);
    }
}
