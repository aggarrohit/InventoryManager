package com.novare.inventoryManager.menus.loginMenu;

import com.novare.inventoryManager.auth.Authenticator;
import com.novare.inventoryManager.auth.PasswordHasher;
import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.menus.adminMenu.AdminMenu;
import com.novare.inventoryManager.menus.cashierMenu.CashierMenu;
import com.novare.inventoryManager.menus.managerMenu.ManagerMenu;
import com.novare.inventoryManager.utils.Menu;
import com.novare.inventoryManager.utils.Storage;

import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

class LoginMenuController {
    private final LoginMenuView view;
    private final LoginMenuModel model;
    private final Storage storage;

    LoginMenuController(LoginMenuModel model, LoginMenuView view) {
        this.model = model;
        this.view = view;
        this.storage = new Storage();
    }

    void switchToEmployeeMenu(Employee currentEmployee) {
        if(!model.isLoggedIn) return;

        switch (currentEmployee.getRole()) {
            case ADMIN -> new AdminMenu(currentEmployee);
            case MANAGER -> new ManagerMenu(currentEmployee);
            case CASHIER -> new CashierMenu(currentEmployee);
            default -> Menu.redirectToHomeMenu();
        }
    }

     Employee login(EmployeeRole role) {
        Employee employee;

        while(true) {
             String socialNumber = Menu.getInput(view.SOCIAL_NUMBER_REQUEST);
             String password = getCustomerPassword(view.PASSWORD_REQUEST);

             employee = storage.getEmployee(socialNumber);

            if (employee == null) {
                view.printSocialNumberInvalidMessage();
                continue;
            }
            if(employee.getRole() != role) {
                view.printInvalidRole();
                Menu.redirectToHomeMenu();
            }

            try{
                model.isLoggedIn = Authenticator.logIn(employee, password) && employee.getRole() == role;
                if(model.isLoggedIn && Objects.equals(employee.getPassword(), employee.getDefaultPassword())) {
                    changePassword(employee);
                }
            }
            catch(NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if(model.isLoggedIn) {
                view.printLoginSuccess();
                return employee;
            }
            else {
                view.printPasswordError();
            }
        }
    }

    private void changePassword(Employee employee) throws NoSuchAlgorithmException {
        view.printChangePasswordOption();
        String newPassword = getCustomerPassword(view.NEW_PASSWORD_REQUEST);
        if (newPassword == null) {
            view.printPasswordError();
            return;
        }

        String hashedPassword = PasswordHasher.hashPassword(newPassword);
        employee.setPassword(hashedPassword);
        storage.updateEmployee(employee);

        view.printPasswordChangeSuccess();
    }
     private String getCustomerPassword(String passwordRequest) {
        Console console = System.console();
        if (console != null) {
            System.out.print(passwordRequest);
            char[] passwordChars = console.readPassword();
            return new String(passwordChars);
        } else {
            return Menu.getInput(passwordRequest);
        }
    }

}
