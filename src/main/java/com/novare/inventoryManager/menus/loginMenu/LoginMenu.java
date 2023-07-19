package com.novare.inventoryManager.menus.loginMenu;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;

public class LoginMenu {
    public LoginMenu(EmployeeRole role) {
        LoginMenuModel model = new LoginMenuModel();
        LoginMenuView view = new LoginMenuView(role);
        LoginMenuController controller = new LoginMenuController(model, view);
        Employee currentEmployee = controller.login(role);

        controller.switchToEmployeeMenu(currentEmployee);
    }
}
