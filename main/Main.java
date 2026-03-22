package main;


import restaurant.Restaurant;
import java.util.Scanner;
// import java.util.ArrayList;
import users.*;
import menuAndFoodItems.*;
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Restaurant restaurant;

    public static void main(String [] args){
        createInitial();
        System.out.println();
        System.out.println("-------" + restaurant.getName().toUpperCase()+ "-------");
        String selection = "";
        while (!selection.equals("exit")){
            System.out.println("Choose an option:");
            System.out.println("1: Customer");
            System.out.println("2: Employee Login");
            System.out.println("3: View Menu");
            System.out.println("Type 'exit' to quit");
            System.out.print("Selection: ");
            selection = scanner.nextLine();
            System.out.println();
            
            switch(selection.toLowerCase()){
                case "1": 
                    customerPortal();
                    break;
                case "2": 
                    employeePortal();
                    break;
                case "3": 
                    restaurant.showMenu();
                    break;
                case "exit":
                    break;
                default: 
                    System.out.println("Invalid Choice");
                    break;
            }
        }
        
    }
    

    private static void createInitial(){
        String name = "Out and In";
        String address = "1357 Newhall Drive";
        String phoneNumber = "(123) - 456 - 789";
        Restaurant outAndIn = new Restaurant(name, address, phoneNumber);
        restaurant = outAndIn;
        outAndIn.createMenu();
        FoodItem burger = new FoodItem("Hamburger", 400, 3.50);
        FoodItem cheeseBurer = new FoodItem("Cheese Burger", 600, 5.50);
        FoodItem fries = new FoodItem("Fries", 300, 3.00);
        FoodItem soda = new FoodItem("Soda", 100, 1.00);
        FoodItem milkeShake = new FoodItem("Milkshake", 400, 2.00);
        outAndIn.addItemToMenu(burger);
        outAndIn.addItemToMenu(cheeseBurer);
        outAndIn.addItemToMenu(fries);
        outAndIn.addItemToMenu(soda);
        outAndIn.addItemToMenu(milkeShake);

        String staffName = "Steve";
        String role = "Cook";
        Double salary = 5.00;
        String staffID = "111";
        Staff cook = new KitchenStaff(staffName, role, salary, outAndIn, staffID);
        restaurant.hireEmployee(cook);

        String managerName = "Bob";
        String bobrole = "Manager";
        Double bobSalar = 10.00;
        String bobStaffID = "333";
        Staff manager = new Manager(managerName, bobrole, bobSalar, outAndIn, bobStaffID);
        restaurant.hireEmployee(manager);

    }

    private static void customerPortal(){ 
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        Customer customer = restaurant.findCustomer(phoneNumber);
        if (customer == null){
            System.out.println("Phone number not found. Creating a new account");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            customer = new Customer(name, phoneNumber, restaurant);
            restaurant.addCustomer(customer);
        }
        customer.customerDuties();

    }

    private static void employeePortal(){
        System.out.print("Enter your staff ID: ");
        String staffID = scanner.nextLine();
        Staff staff = restaurant.findStaff(staffID);
        if (staff == null){
            System.out.println("Invalid ID");
            return;
        }
        staff.performDuties();
    }



}
