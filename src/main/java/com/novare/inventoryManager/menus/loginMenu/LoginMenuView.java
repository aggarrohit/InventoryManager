package com.novare.inventoryManager.menus.loginMenu;

import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.ConsoleMessage;
import com.novare.inventoryManager.utils.Menu;

class LoginMenuView {
    final String SOCIAL_NUMBER_REQUEST = "Enter your Social Security Number (format: yyyy-abc): ";
    final String PASSWORD_REQUEST = "Enter password: ";
    public static final String NEW_PASSWORD_REQUEST = "Enter your new password: ";

    LoginMenuView(EmployeeRole role) {
        printMenuTitle(role);
    }

    private void printMenuTitle(EmployeeRole role) {
        Menu.printLine();
        ConsoleMessage.showInfoMessage("You are going to log in as " + role.getDescription());
        Menu.printLine();
    }

    void printSocialNumberInvalidMessage() {
        ConsoleMessage.showErrorMessage("Employee not found. Please check your social number and try again.");
    }

    void printLoginSuccess() {
        ConsoleMessage.showSuccessMessage("Login successful! Welcome!");
    }

    void printPasswordError() {
        ConsoleMessage.showErrorMessage("Invalid password. Please try again.");
    }
    void printPasswordChangeSuccess() {
        ConsoleMessage.showSuccessMessage("The password changed successfully!");
    }

    void printInvalidRole() {
        ConsoleMessage.showErrorMessage("Wrong role selected. Please choose a valid role.");
    }

    public void printChangePasswordOption() {
        ConsoleMessage.showInfoMessage("Please change your password: ");
    }
//    void printLoginError() {
//        ConsoleMessage.showErrorMessage("Login failed. Please try again");
//    }
}
