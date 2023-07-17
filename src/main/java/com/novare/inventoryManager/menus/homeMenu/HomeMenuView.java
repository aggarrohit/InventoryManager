package com.novare.inventoryManager.menus.homeMenu;

import com.novare.inventoryManager.utils.ConsoleMessage;
import com.novare.inventoryManager.utils.Menu;

import java.util.List;

class HomeMenuView {
    HomeMenuView(List<String> menuOptions) {
        printAppTitle();
        System.out.println("Sign in as: ");

        Menu.printOptions(menuOptions);
        Menu.printQuitOption();
        Menu.printRequest();
    }


    void printAppTitle() {
       ConsoleMessage.showSuccessMessage("--Welcome to the Inventory Manager System--");
        Menu.printLine();
    }

}
