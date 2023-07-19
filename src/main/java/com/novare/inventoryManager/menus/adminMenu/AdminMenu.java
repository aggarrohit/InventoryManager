package com.novare.inventoryManager.menus.adminMenu;

import com.novare.inventoryManager.employees.Employee;

public class AdminMenu {
    public AdminMenu(Employee employee) {
        AdminMenuModel model = new AdminMenuModel();
        AdminMenuView view = new AdminMenuView(employee, model.menuOptions);
        AdminMenuController controller = new AdminMenuController(model, view);

        controller.requestUserInput();
    }
}
