package main;

import restaurant.Restaurant;

import utilities.exceptions.MaxInstancesException;
import users.*;
import menuAndFoodItems.*;
// import order.Order;
import ratings.Rating;

public class Main {

    private static Restaurant restaurant;

    public static void main(String[] args) {
        createInitial();
        restaurant.startup();

    }

    private static void createInitial() {
        String name = "Out and In";
        String address = "1357 Newhall Drive";
        String phoneNumber = "(123) - 456 - 789";
        try {
            Restaurant outAndIn = new Restaurant(name, address, phoneNumber);
            restaurant = outAndIn;

            outAndIn.createMenu();
            try {
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
            } catch (MaxInstancesException e) {
                System.out.println(e.getMessage());
            }

            String staffName = "Steve";
            String role = "KitchenStaff";
            Double salary = 5.00;
            String staffID = "111";
            Staff cook;
            try {
                cook = new KitchenStaff(staffName, role, salary, outAndIn, staffID);
                restaurant.hireEmployee(cook);
            } catch (MaxInstancesException e) {
                System.out.println(e.getMessage());
            }

            String managerName = "Bob";
            String bobrole = "Manager";
            Double bobSalar = 10.00;
            String bobStaffID = "333";
            Staff manager;
            try {
                manager = new Manager(managerName, bobrole, bobSalar, outAndIn, bobStaffID);
                restaurant.hireEmployee(manager);
            } catch (MaxInstancesException e) {
                System.out.println(e.getMessage());
            }

            String customerName = "Conrad";
            int stars = 5;
            String message = "This restaurant is so good if someone made this into an assignment I would give them 100%!!!!!";
            Rating rating = new Rating(customerName, stars, message);
            restaurant.addRating(rating);
        } catch (MaxInstancesException e) {
            System.out.println(e.getMessage());
        }

    }

}
