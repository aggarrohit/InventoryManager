package com.novare.inventoryManager.menus.adminMenu;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.ConsoleMessage;
import com.novare.inventoryManager.utils.Menu;

import java.util.List;

class AdminMenuView {
     final String FULL_NAME_REQUEST = "Enter employee's full name: ";
     final String SOCIAL_NUMBER_REQUEST = "Enter the person's social security number (format: yyyy-abc): ";
     final String SALARY_REQUEST = "Salary: ";

     AdminMenuView(Employee employee, List<String> menuOptions) {
          Menu.printGreeting(employee);
          Menu.displayMenu(menuOptions);
     }

     void printAddNewEmployeeRequest(EmployeeRole role) {
          Menu.printMenuHeader("Add a new " + role.getDescription());
     }

     void printRegisterSuccessMessage() {
          ConsoleMessage.showSuccessMessage("New customer was registered successfully.");
     }

    public void printInvalidSalary() {
          ConsoleMessage.showErrorMessage("Salary must be a number higher than 0. Please try again.");
    }
}