package com.novare.inventoryManager.utils;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.menus.homeMenu.HomeMenu;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Menu() {}

    public static void printMenuHeader(String header) {
        ConsoleMessage.showSuccessMessage("-- " + header + " --");
        System.out.println();
    }

    public static  void printGreeting(Employee employee) {
        System.out.println();
        ConsoleMessage.showSuccessMessage("--Hello " + employee.getRole().getDescription() + " " + employee.getFullName() + "--");
        System.out.println();
    }

    public static void printRequest() {
        System.out.print("Choose an option and press enter: ");
    }

    public static void printOptions(List<String> options) {
        for(int i = 0; i < options.size(); i++) {
            System.out.println((i+ 1) +". " + options.get(i));
        }
    }

    public static String getInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (input.isEmpty()) {
            System.out.print(message);
            input = scanner.nextLine();

            if (input.isEmpty()) {
                printInvalidOption();
            }
        }
        return input.trim();
    }

    public static void displayMenu(List<String> menuOptions) {
        ConsoleMessage.clearConsole();

        Menu.printLine();
        Menu.printOptions(menuOptions);
        Menu.printLine();

        Menu.printRequest();

    }

    public static void printInvalidOption() {
        ConsoleMessage.showErrorMessage("⚠️ Invalid input. Please try again.");
    }

    public static void redirectToHomeMenu() {
        new HomeMenu();
    }

    public static void printGoodbyeMessage() {
        ConsoleMessage.showSuccessMessage("Goodbye! Thank you for using the application.");
    }

    public static void printQuitOption() {
        printLine();
        ConsoleMessage.showInfoMessage("Press 'q' to quit the application");
        printLine();
    }

    public static void printLine() {
        System.out.println();
    }
}
