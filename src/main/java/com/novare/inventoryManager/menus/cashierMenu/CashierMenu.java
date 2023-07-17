package com.novare.inventoryManager.menus.cashierMenu;

import com.novare.inventoryManager.employees.Employee;

public class CashierMenu {
    public CashierMenu(Employee employee) {
        CashierMenuModel model = new CashierMenuModel();
        CashierMenuView view = new CashierMenuView(employee, model.menuOptions);
        CashierMenuController controller = new CashierMenuController(model, view);

        controller.requestUserInput();
    }

}
