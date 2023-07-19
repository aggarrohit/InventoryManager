package com.novare.inventoryManager.menus.homeMenu;

import com.novare.inventoryManager.employees.EmployeeRole;

import java.util.List;

class HomeMenuModel {
    private final List<String> menuOptions = EmployeeRole.getEmployeeRoleNames();

     List<String> getMenuOptions() {
        return  menuOptions;
    }
}
