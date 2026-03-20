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
        System.out.println("Hello! What would you like to do?");
        String selection = "";
        while (!selection.equals("exit")){
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1: Customer");
            System.out.println("2: Employee Login");
            System.out.println("3: View Menu");
            System.out.println("Type 'Exit' to quit");
            System.out.print("Selection: ");
            selection = scanner.nextLine();
            System.out.println();
            
            switch(selection.toLowerCase()){
                case "1": 
                    customerPortal();
                    break;
                // case "2": 
                    // employeePortal();
                    // break;
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


    }

    private static void customerPortal(){
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        Customer customer = restaurant.findCustomer(phoneNumber);
        if (customer == null){
            System.out.println("Phone number not found. Creating a new account");
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            customer = new Customer(name, phoneNumber, restaurant);
            restaurant.addCustomer(customer);
        }
        String selection = "";
        while (!selection.equals("3")){
            System.out.println("1: Create new order");
            System.out.println("2: View previous orders");
            System.out.println("3: Go back to main menu");
            selection = scanner.nextLine();

            switch (selection){
                case "1":
                    customer.createNewOrder();
                    break;
                case "2":
                    customer.viewOrders();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }

    }

    // private static void employeePortal(){
    //     System.out.println("Enter your staff ID");
    //     String staffID = scanner.nextLine();
    //     staff = restaurant.findStaff(staffID);
    //     if (staff == null){
    //         System.out.println("Invalid ID");
    //         return;
    //     }
    //     staff.performDuties();
    // }



}
