package com.novare.inventoryManager.menus.managerMenu;

import java.util.List;

class ManagerMenuModel {
    List<String> menuOptions = List.of(
            "View the inventory products/entries",
            "Generate a report",
            "Add a new product",
            "Create a purchase order",
            "Update product threshold quantity",
            "Update product price",
            "Open the group chat",
            "Sign out"
    );
}
