package com.novare.inventoryManager.auth;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.ConsoleMessage;
import com.novare.inventoryManager.utils.Storage;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public class Registrator {
    private final Storage storage;

    public Registrator() {
        this.storage = new Storage();
    }

    public Employee registerEmployee(String fullName, String socialNumber, String employmentNumber,  BigDecimal salary, EmployeeRole role) throws NoSuchAlgorithmException {
        if(!Validator.validateSocialNumber(socialNumber)) {
            registrationFailedMessage();
            invalidSocialNumberWarning();
            return null;
        }
        Employee employee = storage.getEmployee(socialNumber);

        if(employee != null) {
            registrationFailedMessage();
            personExistWarning();
            return null;
        }
        Employee newEmployee = new Employee(fullName, socialNumber, employmentNumber, salary, role);
        storage.saveEmployee(newEmployee);

        return newEmployee;
    }

    private void registrationFailedMessage() {
        ConsoleMessage.showErrorMessage("Registration failed!");
    }

    private void invalidSocialNumberWarning() {
        ConsoleMessage.showErrorMessage("Invalid social number!");
    }
    private void personExistWarning() {
        ConsoleMessage.showErrorMessage("User with the same social number already exists.");
    }

}
