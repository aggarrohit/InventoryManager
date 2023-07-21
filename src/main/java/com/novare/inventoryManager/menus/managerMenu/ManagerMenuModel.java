package com.novare.inventoryManager.menus.managerMenu;

import java.util.List;

class ManagerMenuModel {
    final List<String> menuOptions = List.of(
            "View the inventory products/entries",
            "View Sales Orders",
            "View Purchase Orders",

            "Add a new product",
            "Create a purchase order",
            "Update product threshold quantity",
            "Update product price",

            "View notifications",
            "Generate a report",
            "Export transaction list",
            "Open the group chat",
            "Sign out"
    );
}
