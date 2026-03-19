package main;


import restaurant.Restaurant;
import java.util.Scanner;
import java.util.ArrayList;
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
            System.out.println("3: Eat at an existing restaurant");
            System.out.println("Type 'Exit' to quit");
            System.out.print("Selection: ");
            selection = scanner.nextLine();
            System.out.println();
            if (selection.equals("1")){
                makeNewResturant();
            } else if(selection.equals("2")){
                manage();
            }
        }
        
    }
    public static Restaurant listRestuants(){
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
        System.out.println("Which resturant do you want to manage:");
        Restaurant manage = listRestuants();
        manageResturant(manage);
    }
    public static void makeNewResturant(){
        System.out.println("What is the Resturant's Name");
        String resturantName = scanner.nextLine();
        Restaurant restaurant = new Restaurant(resturantName);
        restaurantList.add(restaurant);
        manageResturant(restaurant);
    }
    public static void manageResturant(Restaurant restaurant){
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




}
