package users;

import restaurant.*;

import java.util.Scanner;

import java.util.Iterator;

import menuAndFoodItems.FoodItem;
import order.Order;
import java.util.ArrayList;
import utilities.UI;
import ratings.Rating;

public class Customer {

    // private Object order ;
    private ArrayList<Order> pastOrders;
    private String name;
    private ArrayList<Order> currentOrders;
    private Order currentOrder;
    // private Object payment;
    private String phoneNumber;
    private Restaurant restaurant;
    private static Scanner scanner = new Scanner(System.in);
    private Rating rating;

    public Customer(String name, String phoneNumber, Restaurant restaurant) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.restaurant = restaurant;
        this.currentOrder = null;
        pastOrders = new ArrayList<>();
        currentOrders = new ArrayList<>();
        this.rating = null;
    }

    public void customerDuties() {
        System.out.println();
        System.out.println();
        System.out.println();
        String selection = "";
        while (!selection.equals("4")) {
            UI.printHeader("CUSTOMER MENU");
            if (currentOrders.size() != 0) {
                checkCurrentOrders();
            }
            System.out.println("Hi " + this.name + "! What would you like to do?");
            System.out.println();
            System.out.println("1) Create new order");
            System.out.println("2) View previous orders");
            System.out.println("3) Leave rating");
            System.out.println("4) Go back to main menu");
            System.out.print("Choice: ");
            selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    createNewOrder();
                    break;
                case "2":
                    viewPastOrders();
                    break;
                case "3":
                    checkRating();
                    break;
                case "4":
                    break;
                default:
                    UI.error("Invalid option.");
                    break;
            }
        }
    }

    private void checkRating() {
        if (this.rating == null) {
            leaveNewRating();
        } else {
            changeRating();
        }
    }

    private void leaveNewRating() {
        UI.printSection("WRITING RATING");
        System.out.print("What is your rating out of 5: ");
        int stars = scanner.nextInt();
        scanner.nextLine();
        if (stars > 5 || stars < 0) {
            UI.error("Rating must be between 0 and 5");
            return;
        }
        System.out.print("Message (Optional): ");
        String message = scanner.nextLine();
        this.rating = new Rating(this.name, stars, message);
        restaurant.addRating(rating);
        UI.success("Left rating!");
    }

    private void changeRating() {
        UI.printSection("CHANGING RATING");
        System.out.println("You have already left a rating.");
        System.out.println("1) Change rating.");
        System.out.println("2) Return");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                restaurant.removeRating(rating);
                leaveNewRating();
                break;
            case "2":
                break;
            default:
                break;
        }
    }

    private void checkCurrentOrders() {
        Iterator<Order> iterator = currentOrders.iterator();
        UI.printSection("CURRENT PENDING ORDERS");
        while (iterator.hasNext()) {
            Order o = iterator.next();

            if (o.getStatus().equals("COMPLETE")) {
                UI.success("Order number " + o.getOrderNumber() + " is ready for pickup!");
                pastOrders.add(o);
                iterator.remove(); // safe removal
            } else {
                UI.info("Order number " + o.getOrderNumber() + " is " + o.getStatus());
                System.out.println();
            }
        }
    }

    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    public void createNewOrder() {
        System.out.println();
        currentOrder = new Order();
        currentOrder.setStatus("IN PROGRESS");
        buildOrder();
    }

    public void recieveOrder() {
        this.currentOrder = null;
    }

    public void buildOrder() {
        restaurant.showMenu();
        int choice = -1;
        while (choice != 0) {
            UI.printSection("BUILD YOUR ORDER");
            System.out.println("Enter an item number to add it");
            System.out.println("Enter 0 to finish your order");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice != 0) {
                FoodItem item = restaurant.getMenu().getItem(choice);
                System.out.println();
                UI.success(item.getName() + " added to your order.");
                currentOrder.addItemToOrder(item);
            }
            UI.printHeader("CURRENT ORDER");
            currentOrder.printOrder();
        }
        payForOrder();

    }

    private void payForOrder() {
        UI.printSection("PAYMENT");
        UI.info("Your order total is: " + UI.money(currentOrder.calculateTotal()));
        System.out.println("1) Card");
        System.out.println("2) Cash");
        System.out.println("3) Cancel Order");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                payWithCard();
                break;
            case "2":
                payWithCash();
                break;
            case "3":
                cancelOrder();
                break;
        }

    }

    private void payWithCard() { // eventually make this call the payment package

        System.out.println();
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter card holder name: ");
        String cardHolder = scanner.nextLine();

        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        System.out.println();
        System.out.println("Processing card payment...");
        System.out.println("Payment successful!");
        currentOrder.setStatus("PAID");
        finishCurrentOrder();
    }

    private void payWithCash() {
        System.out.println();
        double total = currentOrder.calculateTotal();
        System.out.println("Total amount due: " + total);

        double cashPaid = 0;

        while (cashPaid < total) {
            System.out.print("Enter cash amount: ");
            cashPaid = scanner.nextDouble();

            if (cashPaid < total) {
                System.out.println("Not enough cash. Please pay at least " + total);
            }
        }

        double change = cashPaid - total;
        System.out.println("Payment successful!");
        System.out.println("Change: " + change);
        currentOrder.setStatus("PAID");

        scanner.nextLine();
        finishCurrentOrder();
    }

    private void finishCurrentOrder() { // add a gerenetate receipt as part of a payable class??
        UI.printHeader("RECEIPT");
        currentOrder.printOrder();
        System.out.println();
        restaurant.addToRevenue(currentOrder.calculateTotal());
        restaurant.addOrder(currentOrder);
        currentOrders.add(currentOrder);
        this.currentOrder = null;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void viewPastOrders() {
        UI.printSection("PAST ORDERS");
        if (pastOrders.size() == 0) {
            UI.info("No past orders found.");
        } else {
            for (Order o : pastOrders) {
                o.printOrder();
                System.out.println();
            }
        }
    }

    public void cancelOrder() {
        this.currentOrder = null;
        UI.success("Order cancelled");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
