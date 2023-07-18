package com.novare.inventoryManager.employees;

import com.novare.inventoryManager.auth.PasswordHasher;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Employee implements Serializable {
    private UUID id;
    private String fullName;
    private String socialNumber;
    private String password;
    private BigDecimal salary;
    private EmployeeRole role;
    private static final long serialVersionUID = 1496276507263397484L;
    private final String DEFAULT_PASSWORD = PasswordHasher.hashPassword("MyPassword123");


    public Employee(String fullName, String socialNumber, BigDecimal salary, EmployeeRole role) throws NoSuchAlgorithmException {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.socialNumber = socialNumber;
        this.password = DEFAULT_PASSWORD;
        this.salary = salary;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
}
