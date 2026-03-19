package main;


import restaurant.Restaurant;
import java.util.Scanner;
import java.util.ArrayList;
import users.*;
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Restaurant> restaurantList = new ArrayList<>();

    public static void main(String [] args){
        System.out.println("Hello! What would you like to do?");
        String selection = "";
        while (!selection.equals("Exit")){
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1: Open a new restaurant");
            System.out.println("2: Manage an existing restaurant");
            System.out.println("3: Create an order at an existing restaurant");
            System.out.println("4: Check on an existing customers order");
            System.out.println("Type 'Exit' to quit");
            System.out.print("Selection: ");
            selection = scanner.nextLine();
            System.out.println();
            if (selection.equals("1")){
                makeNewRestaurant();
            } else if(selection.equals("2")){
                manage();
            }
        }
        
    }
    public static Restaurant listResturants(){
        for (int i=0; i<restaurantList.size(); i++){
            System.out.println(i+1 + ": " + restaurantList.get(i));
        }
        System.out.println("Selection: ");
        int j = scanner.nextInt();
        return restaurantList.get(j-1);
    }
    public static void manage(){
        if (restaurantList.size() == 0){
            System.out.println("There are no restaurants to manage");
            return;
        }
        System.out.println("Which restaurant do you want to manage:");
        Restaurant manage = listResturants();
        manageRestaurant(manage);
    }
    public static void makeNewRestaurant(){
        System.out.println("What is the Resaturant's Name");
        String restaurantName = scanner.nextLine();
        Restaurant restaurant = new Restaurant(restaurantName);
        restaurantList.add(restaurant);
        manageRestaurant(restaurant);
    }
    public static void manageRestaurant(Restaurant restaurant){
        String input = "";
        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Set address");
            System.out.println("2. Create menu");
            System.out.println("3. Add item to menu");
            System.out.println("4. Remove item from menu");
            System.out.println("5. Show menu");
            System.out.println("Type 'exit' to return");
            System.out.print("Selection: ");
            input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "1":
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    restaurant.setAddress(address);
                    break;
                case "2":
                    restaurant.createMenu();
                    System.out.println("Menu created.");
                    break;
                case "3":
                    restaurant.addItemToMenu();
                    break;
                case "4":
                    restaurant.removeItem();
                    break;
                case "5":
                    restaurant.showMenu();
                    break;
                case "exit":
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void createCustomer(){
        if (restaurantList.size() == 0){
            System.out.println("There are no restaurants to eat at");
            return;
        }
        System.out.println("What is your name: ");
        String name = scanner.nextLine();
        Customer customer = new Customer(name);
        System.out.println("Which restaurant do you want to eat at:");
        Restaurant eat = listResturants();
        // customer.setRestaurant(eat);
        eatAtRestaurant(customer);
    }

    public static void eatAtRestaurant(Customer customer){
        

        /* Check if customers order is null
        if order is null then 
        Customer creates a new order... customer.createOrder()
        print restaurant menu 
        while loop where while order is not complete
        customer.getOrder().getStatus() == "not complete"
        ask what they want to add to their order
        have an option for when they think order is complete
        if order is complete then add to restaurant order list
        do payment??
        print the order
        move into new while loop where while order is not complete have options to check order status or cancel??
        after order is complete, print its complete and awaiting pickup
        if customer.getRating() == null then 
        they can leave a rating???
        create a new order?? else exit
        */
    }



}
