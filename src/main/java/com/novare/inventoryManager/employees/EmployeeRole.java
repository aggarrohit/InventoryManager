package com.novare.inventoryManager.employees;

import java.util.ArrayList;
import java.util.List;

public enum EmployeeRole {
    ADMIN("Admin"), MANAGER("Manager"), CASHIER("Cashier");
    private String description;

    EmployeeRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<String> getEmployeeRoleNames() {
        EmployeeRole[] employeeRoles = EmployeeRole.values();
        List<String> roleNames = new ArrayList<>();

        for(EmployeeRole role : employeeRoles) {
            roleNames.add(role.description);
        }
        return roleNames;
    }
}
