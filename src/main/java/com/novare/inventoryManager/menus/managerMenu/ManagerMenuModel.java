package com.novare.inventoryManager.menus.managerMenu;

import java.util.List;

class ManagerMenuModel {
    final List<String> menuOptions = List.of(
            "View the inventory products/entries",
            "Generate a report",
            "Add a new product",
            "Create a purchase order",
            "Update product threshold quantity",
            "Update product price",
            "View notifications",
            "Export transaction list",
            "Open the group chat",
            "View Sales Orders",
            "View Purchase Orders",
            "Sign out"
    );
}
