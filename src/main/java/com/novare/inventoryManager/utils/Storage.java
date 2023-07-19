package com.novare.inventoryManager.utils;

import com.novare.inventoryManager.employees.Employee;

import java.util.ArrayList;
import java.util.UUID;

public class Storage {
    private final FileManager fileManager;
    private final String EMPLOYEES_FILE = "data/employees.txt";
    public Storage() {
        this.fileManager = new FileManager();
    }

    public ArrayList<Employee> getEmployees() {
        return fileManager.readObjects(EMPLOYEES_FILE);
    }

    public void saveEmployee(Employee newEmployee) {
        ArrayList<Employee> employees = getEmployees();
        employees.add(newEmployee);
        fileManager.writeObjects(EMPLOYEES_FILE, employees);
    }

    public Employee getEmployee(UUID id) {
        var employees = getEmployees();
        for(Employee employee : employees) {
            if(employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(String socialNumber) {
        var employees = getEmployees();
        for(Employee employee : employees) {
            if(employee.getSocialNumber().equals(socialNumber)) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployee(Employee updatedEmployee) {
        var employees = getEmployees();
        for(int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if(employee.getId().equals(updatedEmployee.getId())) {
                employees.set(i, updatedEmployee);
                fileManager.writeObjects(EMPLOYEES_FILE, employees);
                return;
            }
        }
    }

    public void deleteEmployee(UUID id) {
        ArrayList<Employee> employees = getEmployees();
        employees.removeIf(employee -> employee.getId().equals(id));
        fileManager.writeObjects(EMPLOYEES_FILE, employees);
    }

}
