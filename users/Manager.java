package users;

import restaurant.Restaurant;
import utilities.UI;
import menuAndFoodItems.FoodItem;
import java.util.ArrayList;

import utilities.Input;

public class Manager extends Staff {

    public Manager(String name, String role, double salary, Restaurant restaurant,
            String staffID) {
        super(name, role, salary, restaurant, staffID);
    }

    @Override
    public void performDuties() {
        // Need to add fire employees, manage restaurant
        String choice = "";
        while (!choice.equals("4")) {
            UI.printHeader("MANAGER MENU");
            System.out.println("1: Manage Menu");
            System.out.println("2: Manage Staff");
            System.out.println("3: Manage Restaurant");
            System.out.println("4: Go back");
            System.out.println();
            System.out.print("Choice: ");
            choice = Input.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    manageMenu();
                    break;
                case "2":
                    manageStaff();
                    break;
                case "3":
                    manageRestaurant();
                    break;
                case "4":
                    break;
                default:
                    UI.error("Invalid choice");
                    break;
            }
        }
    }

    private void manageMenu() {

        String choice = "";
        while (!choice.equals("5")) {
            UI.printHeader("MENU MANAGEMENT");
            System.out.println("1: Add item to menu");
            System.out.println("2: Remove item from menu");
            System.out.println("3: Change item price");
            System.out.println("4: View menu");
            System.out.println("5: Go back");
            System.out.println();
            System.out.print("Choice: ");
            choice = Input.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    addItemToMenu();
                    break;
                case "2":
                    removeItemFromMenu();
                    break;
                case "3":
                    changeItemPrice();
                    break;
                case "4":
                    viewMenu();
                    break;
                case "5":
                    break;
                default:
                    UI.error("Invalid choice");
                    break;
            }
        }
    }

    private void manageStaff() {

        String choice = "";
        while (!choice.equals("5")) {
            UI.printHeader("STAFF MANAGEMENT");
            System.out.println("1: View all staff");
            System.out.println("2: Hire new staff");
            System.out.println("3: Fire staff");
            System.out.println("4: Increase staff salary");
            System.out.println("5: Go back");
            System.out.println();
            System.out.print("Choice: ");
            choice = Input.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    viewAllStaff();
                    break;
                case "2":
                    hireEmployee();
                    break;
                case "3":
                    fireStaff();
                    break;
                case "4":
                    increaseSalary();
                    break;
                case "5":
                    break;
                default:
                    UI.error("Invalid choice");
                    break;
            }
        }
    }

    private void manageRestaurant() {
        String choice = "";
        while (!choice.equals("4")) {
            UI.printHeader("RESTAURANT MANAGEMENT");
            System.out.println("1: Change phone number");
            System.out.println("2: Change address");
            System.out.println("3: View revenue");
            System.out.println("4: Go back");
            System.out.println();
            System.out.print("Choice: ");
            choice = Input.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    changePhoneNumber();
                    break;
                case "2":
                    changeAddress();
                    break;
                case "3":
                    viewRevenue();
                    break;
                case "4":
                    break;
                default:
                    UI.error("Invalid choice");
                    break;
            }
        }
    }

    private void viewRevenue() {
        UI.printSection("REVENUE");
        restaurant.printRevenue();
    }

    private void changePhoneNumber() {
        System.out.print("Enter the new phone number: ");
        String number = Input.getString();
        restaurant.setPhoneNumber(number);
        UI.success("Phone number changed");
    }

    private void changeAddress() {
        System.out.print("Enter the new address: ");
        String address = Input.getString();
        restaurant.setAddress(address);
        UI.success("Address changed");
    }
    // ========== MENU MANAGEMENT METHODS (NOW IMPLEMENTED) ==========

    private void addItemToMenu() {
        System.out.print("Enter item name: ");
        String name = Input.getString();

        System.out.print("Enter calories: ");
        int calories;
        try {
            calories = Input.getInt();
            if (calories < 0) {
                UI.error("Calories cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            UI.error("Invalid number format.");
            return;
        }

        System.out.print("Enter price: ");
        double price;
        try {
            price = Input.getDouble();
            if (price <= 0) {
                UI.error("Price must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            UI.error("Invalid price format.");
            return;
        }

        FoodItem newItem = new FoodItem(name, calories, price);
        restaurant.addItemToMenu(newItem);
        UI.success(name + " added to menu successfully!");
    }

    private void removeItemFromMenu() {
        System.out.print("Enter item name to remove: ");
        String name = Input.getString();
        restaurant.removeItem(name);
    }

    private void changeItemPrice() {
        System.out.print("Enter item name: ");
        String name = Input.getString();

        System.out.print("Enter new price: ");
        double newPrice;
        try {
            newPrice = Input.getDouble();
            if (newPrice <= 0) {
                UI.error("Price must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            UI.error("Invalid price format.");
            return;
        }

        restaurant.changePrice(name, newPrice);
    }

    private void viewMenu() {
        restaurant.showMenu();
    }

    // ========== STAFF MANAGEMENT METHODS (PLACEHOLDERS) ==========

    private void hireEmployee() {
        System.out.print("Enter employee name: ");
        String empName = Input.getString();

        System.out.print("Enter role (KitchenStaff/Manager): ");
        String empRole = Input.getString();

        System.out.print("Enter salary: ");
        double empSalary;
        try {
            empSalary = Input.getDouble();
            if (empSalary <= 0) {
                UI.error("Salary must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            UI.error("Invalid salary format.");
            return;
        }

        System.out.print("Enter staff ID: ");
        String empID = Input.getString();

        // Check if ID already exists
        if (restaurant.findStaff(empID) != null) {
            UI.error("Staff ID already exists!");
            return;
        }

        Staff newEmployee;
        if (empRole.equalsIgnoreCase("Manager")) {
            newEmployee = new Manager(empName, empRole, empSalary, restaurant, empID);
        } else {//add another case later
            newEmployee = new KitchenStaff(empName, empRole, empSalary, restaurant, empID);
        }

        restaurant.hireEmployee(newEmployee);
        UI.success(empName + " hired successfully as " + empRole + "!");
    }

    private void fireStaff() {
        System.out.print("What is the Staff ID: ");
        String ID = Input.getString();
        Staff fired = restaurant.findStaff(ID); //add exception here if staff doesnt exist
        if (fired.getRole().equals("Manager")) {
            UI.error("You can not fire another manager");
        } else {
            restaurant.fireStaff(fired);
            UI.success("Fired " + fired.getName());
        }
    }

    private void increaseSalary() {
        System.out.print("What is the Staff ID: ");
        String ID = Input.getString();
        Staff raise = restaurant.findStaff(ID); //add exception here if staff no exist
        if (raise.getRole().equals("Manager")) {
            UI.error("You can not raise another manager's salary");
        } else {
            System.out.print("What is the new salary for the staff: ");
            try{
                double newSalary = Input.getDouble();
                raise.setSalary(newSalary);
                UI.success("Gave raised to " + raise.getName());
            } catch (NumberFormatException e){
                UI.error("Invalid salary type");
            }
            
            
        }
    }

    private void viewAllStaff() {
        UI.printHeader("ALL STAFF");

        ArrayList<Staff> staffList = restaurant.getStaffList();

        if (staffList == null || staffList.isEmpty()) {
            System.out.println("No staff members found.");
            return;
        }

        System.out.println("ID\tName\tSalary\tRole");
        System.out.println("--------------------------------");
        for (Staff s : staffList) {
            System.out
                    .println(s.getStaffID() + "\t" + s.getName() + "\t" + UI.money(s.getSalary()) + "\t" + s.getRole());
        }
        System.out.println();
    }
}
