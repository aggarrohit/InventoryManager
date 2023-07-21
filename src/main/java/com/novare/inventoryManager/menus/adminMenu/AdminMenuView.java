package com.novare.inventoryManager.menus.adminMenu;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.ConsoleMessage;
import com.novare.inventoryManager.utils.Menu;
import com.novare.inventoryManager.utils.Table;

import java.util.ArrayList;
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
     void printInvalidSalary() {
          ConsoleMessage.showErrorMessage("Salary must be a number higher than 0. Please try again.");
    }

     void printEmployeesTable(List<Employee> employees) {
          List<Integer> columnWidths = List.of(25, 15, 15, 15);
          List<String> headers = List.of("Full name", "Role", "Social Number", "Salary");
          List<List<String>> rows = new ArrayList<>();

          for (Employee employee : employees) {
               String name = employee.getFullName();
               String role = employee.getRole().getDescription();
               String socialNumber = employee.getSocialNumber();
               String salary = employee.getSalary().toString();

               List<String> newRow = List.of(name, role, socialNumber, salary);
               rows.add(newRow);
          }

          Table table = new Table(columnWidths, headers, rows,"Employees");
          table.showData();
     }


}
