package com.novare.inventoryManager.menus.adminMenu;

import com.novare.inventoryManager.auth.Registrator;
import com.novare.inventoryManager.auth.Validator;
import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.groupChat.GroupChat;
import com.novare.inventoryManager.inventory.Inventory;
import com.novare.inventoryManager.order.SalesOrderInventory;
import com.novare.inventoryManager.reports.SalesStatisticsReport;
import com.novare.inventoryManager.utils.Menu;
import com.novare.inventoryManager.utils.Storage;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class AdminMenuController {
    private final AdminMenuModel model;
    private final AdminMenuView view;
    private final Scanner scanner;
    private final Employee employee;
    AdminMenuController(AdminMenuModel model, AdminMenuView view,Employee employee) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.employee = employee;
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
                case 3 -> {
                    Storage storage = new Storage();
                    view.printEmployeesTable(storage.getEmployees());
                }
                case 4 -> Inventory.listInventory();
                case 5 -> {
                    SalesOrderInventory salesOrderInventory = new SalesOrderInventory();
                    SalesStatisticsReport.generate(salesOrderInventory.getMostSoldItemsStatistics());
                }
                case 6 -> new GroupChat(employee);

                case 7 -> Menu.redirectToHomeMenu();
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
