package com.novare.inventoryManager.menus.managerMenu;

import com.novare.inventoryManager.employees.Employee;

public class ManagerMenu {
    public ManagerMenu(Employee employee) {
        ManagerMenuModel model = new ManagerMenuModel();
        ManagerMenuView view = new ManagerMenuView(employee, model.menuOptions);
        ManagerMenuController controller = new ManagerMenuController(model, view);
        controller.requestUserInput();
    }
}
