package users;

import restaurant.Restaurant;
import java.util.Scanner;
import order.Order;

public class KitchenStaff extends Staff{

    private Scanner scanner = new Scanner(System.in);
    public KitchenStaff(String name, String role, double salary, Restaurant restaurant, String staffID) {
        super(name, role, salary, restaurant, staffID);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void performDuties() {
        System.out.println();
        System.out.println("1: View orders.");
        System.out.println("2: Mark an order as preparing.");
        System.out.println("3: Mark an order as complete");
        System.out.println("4: To go back");
        System.out.println("Type the number of what task you want to perform");
        String choice = "";
        while (!choice.equals("4")){
            System.out.println();
            System.out.print("Choice: ");
            choice = scanner.nextLine();
            System.out.println();
            switch (choice){
                case "1":
                    viewPendingOrders();
                    break;
                case "2":
                    markOrderAsPreparing();
                    break;
                case "3":
                    markOrderAsComplete();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private void viewPendingOrders(){
        restaurant.viewOrders();
    }

    private void markOrderAsPreparing(){
        System.out.print("Enter the order number: ");
        int orderID = scanner.nextInt();
        scanner.nextLine();
        Order order = restaurant.findOrder(orderID);
        if (order != null){
            System.out.println("Changing order to preparing");
            order.setStatus("PREPARING");
        }
        else{
            System.out.println("No Order Found");
        }

    }
    private void markOrderAsComplete(){
        System.out.print("Enter the order number: ");
        int orderID = scanner.nextInt();
        scanner.nextLine();
        Order order = restaurant.findOrder(orderID);
        if (order != null){
            System.out.println("Changing order to complete");
            order.setStatus("COMPLETE");
        }
        else{
            System.out.println("No Order Found");
        }
    }
    
}
