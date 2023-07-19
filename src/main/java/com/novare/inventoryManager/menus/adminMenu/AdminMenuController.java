package com.novare.inventoryManager.menus.adminMenu;

import com.novare.inventoryManager.auth.Registrator;
import com.novare.inventoryManager.auth.Validator;
import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.utils.Menu;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class AdminMenuController {
    private final AdminMenuModel model;
    private final AdminMenuView view;
    private final Scanner scanner;
    AdminMenuController(AdminMenuModel model, AdminMenuView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    void requestUserInput() {
        String input = scanner.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);
            handleOption(selectedOption);
        }
        catch (NumberFormatException | IndexOutOfBoundsException exception) {
                Menu.printInvalidOption();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    void handleOption(int selectedOption) throws IndexOutOfBoundsException, NoSuchAlgorithmException {
        try {
            switch (selectedOption) {
                case 1 -> addNewEmployee(EmployeeRole.MANAGER);
                case 2 -> addNewEmployee(EmployeeRole.CASHIER);

                case 3 -> Inventory.listInventory();
                case 4 -> System.out.println("TODO: Generate report"); //sprint 2
                case 5 -> System.out.println("TODO: Open the group chat"); // sprint 2

                case 6 -> Menu.redirectToHomeMenu();
                default -> Menu.printInvalidOption();
            }
        }
        catch(IndexOutOfBoundsException exception) {
            Menu.printInvalidOption();
        }
        catch(NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
        runMenu();
    }


    void runMenu(){
        Menu.displayMenu(model.menuOptions);
        requestUserInput();
    }

    boolean addNewEmployee(EmployeeRole role) throws NoSuchAlgorithmException {
        Registrator registrator = new Registrator();
        Employee newEmployee;

        view.printAddNewEmployeeRequest(role);

        while (true) {
            String fullName = Menu.getInput(view.FULL_NAME_REQUEST);

            String socialNumber = Menu.getInput(view.SOCIAL_NUMBER_REQUEST);
            while(!Validator.validateSocialNumber(socialNumber)) {
                Menu.printInvalidOption();
                socialNumber = Menu.getInput(view.SOCIAL_NUMBER_REQUEST);
            }

            BigDecimal salary;
            while (true) {
                try {
                    salary = new BigDecimal(Menu.getInput(view.SALARY_REQUEST));
                    if (salary.compareTo(BigDecimal.ZERO) <= 0) {
                        view.printInvalidSalary();
                    }
                    else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    view.printInvalidSalary();
                }
            }

            newEmployee = registrator.registerEmployee(fullName, socialNumber, salary, role);

            if (newEmployee != null) {
               view.printRegisterSuccessMessage();
                return true;
            }
        }
    }


}
