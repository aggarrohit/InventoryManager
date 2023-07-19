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

    public void printChangePasswordRequest() {
        Menu.printLine();
        printChangePasswordOption();
        printRequiredPasswordFormat();
        Menu.printLine();
    }
    public void printRequiredPasswordFormat() {
        ConsoleMessage.showInfoMessage("Password should contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and be at least 8 characters long.");
    }

    public void printPasswordEqualWarning() {
        ConsoleMessage.showErrorMessage("New password should not be the same as the default password. Please try again.");
    }
}
