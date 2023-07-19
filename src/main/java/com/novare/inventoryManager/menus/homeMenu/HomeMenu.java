package com.novare.inventoryManager.menus.homeMenu;

public class HomeMenu {
    public HomeMenu() {
        HomeMenuModel model = new HomeMenuModel();
        HomeMenuView view = new HomeMenuView(model.getMenuOptions());
        HomeMenuController controller = new HomeMenuController(model, view);

        controller.requestUserInput();
    }
}
