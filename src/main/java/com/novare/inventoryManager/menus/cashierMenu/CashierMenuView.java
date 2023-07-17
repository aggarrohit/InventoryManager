package com.novare.inventoryManager.menus.cashierMenu;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.utils.Menu;

import java.util.List;

class CashierMenuView {

    CashierMenuView(Employee employee, List<String> menuOptions) {
        Menu.printGreeting(employee);
        Menu.displayMenu(menuOptions);
    }
}
