package users;

import restaurant.*;

import java.util.Scanner;

import menuAndFoodItems.FoodItem;
import order.Order;
import java.util.ArrayList;

public class Customer {

    // private Object order ;
    private ArrayList<Order> orders;
    private String name;
    private Order currentOrder;
    // private Object payment;
    private String phoneNumber;
    private Restaurant restaurant;
    private static Scanner scanner = new Scanner(System.in);


    public Customer(String name, String phoneNumber, Restaurant restaurant){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.restaurant = restaurant;
        this.currentOrder = null;
        orders = new ArrayList<>();
    }

    public Order getCurrentOrder(){
        return this.currentOrder;
    }
    public void createNewOrder() {
        System.out.println("USER: " + name + " started a new order.");
        currentOrder = new Order();
        currentOrder.setStatus("IN PROGRESS");
        buildOrder();
    }
    public void buildOrder(){
        currentOrder.printOrder();
        restaurant.showMenu();
        System.out.println();
        int choice = -1;
        while (choice != 0){
            System.out.println("Type the index of the order item you want to add to your order or type 0 to finish your order");
            choice = scanner.nextInt();
            if (choice!=0){
                FoodItem item = restaurant.getMenu().getItem(choice);
                System.out.println("Adding " + item.getName()+ " to order.");
                currentOrder.addItemToOrder(item);
            }
        }
        System.out.println("YOUR ORDER:");
        currentOrder.printOrder();
        currentOrder.setStatus("PENDING PAYMENT");
        restaurant.addOrder(currentOrder);
        orders.add(currentOrder);
        
        
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
    public void viewOrders(){
        for(Order o: orders){
            o.printOrder();
        }
    }
    public void cancelOrder() {
        // this.order = null;
        System.out.println("USER: " + name + " cancelled their order.");
    }

    public void makePayment() {
        System.out.println("SYSTEM: " + name + " is processing payment.");
    }

    public void printOrder() {
        System.out.println("DISPLAY: Printing receipt for " + name);
    }

    public void trackOrderStatus() {
        System.out.println("CHECK: Tracking order for " + name);
    }

    public void createRating() {
        System.out.println("USER: " + name + " is submitting a review.");
    }

    public String getName(){ 
        return name; 
    }
    
    public void setName(String name){ 
        this.name = name; 
    }

}
