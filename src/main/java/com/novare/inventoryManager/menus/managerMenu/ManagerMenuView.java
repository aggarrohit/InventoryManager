package com.novare.inventoryManager.menus.managerMenu;


import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.utils.Menu;

import java.util.List;

class ManagerMenuView {
    ManagerMenuView(Employee employee, List<String> menuOptions) {
        Menu.printGreeting(employee);
        Menu.displayMenu(menuOptions);
    }

}
