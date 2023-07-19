package com.novare.inventoryManager.auth;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.Storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public class RegistratorTest {
    Registrator registrator;
    Storage storage;

    @BeforeEach
    void setUp() {
        registrator = new Registrator();
        storage = new Storage();
    }

    @Test
    void testRegisterEmployeeSuccess() throws NoSuchAlgorithmException {
        String fullName = "John Snow";
        String socialNumber = "2000-fgh";
        BigDecimal salary = new BigDecimal("35000");
        EmployeeRole role = EmployeeRole.MANAGER;

        Employee newEmployee = registrator.registerEmployee(fullName, socialNumber, salary, role);

        Assertions.assertNotNull(newEmployee);
        Assertions.assertEquals(fullName, newEmployee.getFullName());
        Assertions.assertEquals(socialNumber, newEmployee.getSocialNumber());
        Assertions.assertEquals(salary, newEmployee.getSalary());
        Assertions.assertEquals(newEmployee.getDefaultPassword(), newEmployee.getPassword());
        Assertions.assertEquals(role, newEmployee.getRole());

        storage.deleteEmployee(newEmployee.getId());
    }

    @Test
    void testRegisterEmployeeWithInvalidSocialNumber() throws NoSuchAlgorithmException {
        String fullName = "John Snow";
        String socialNumber = "2002fgh";
        BigDecimal salary = new BigDecimal("35000");
        EmployeeRole role = EmployeeRole.MANAGER;

        Employee newEmployee = registrator.registerEmployee(fullName, socialNumber, salary, role);

        Assertions.assertNull(newEmployee);
    }

    @Test
    void testRegisterEmployeeWithExistingSocialNumber() throws NoSuchAlgorithmException {
        String fullName1 = "John Snow";
        String socialNumber1 = "2002-fgh";
        BigDecimal salary1 = new BigDecimal("35000");
        EmployeeRole role1 = EmployeeRole.MANAGER;

        Employee existingEmployee = registrator.registerEmployee(fullName1, socialNumber1, salary1, role1);

        String fullName2 = "Jane Smith";
        String socialNumber2 = socialNumber1;
        BigDecimal salary2 = new BigDecimal("20000");
        EmployeeRole role2 = EmployeeRole.CASHIER;

        Employee newEmployee = registrator.registerEmployee(fullName2, socialNumber2, salary2, role2);

        Assertions.assertNull(newEmployee);

    }

}
