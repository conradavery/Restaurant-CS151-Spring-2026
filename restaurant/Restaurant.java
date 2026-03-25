package restaurant;

import java.util.ArrayList;
import menuAndFoodItems.*;
import users.*;
import order.Order;
import utilities.Input;
import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.MenuItemNotFoundException;
import utilities.exceptions.OrderNotFoundException;
import utilities.exceptions.StaffNotFoundException;
import ratings.Rating;

public class Restaurant {
    private String name;
    private String address;
    private Menu menu;
    private String phoneNumber;
    private ArrayList<Staff> staffList;
    private ArrayList<Customer> customerList;
    private ArrayList<Order> orders;
    private double revenue;
    private ArrayList<Rating> ratings;
    private static int restaurantCount = 0;

    public Restaurant(String name, String address, String phoneNumber) throws MaxInstancesException {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menu = null;
        staffList = new ArrayList<>();
        orders = new ArrayList<>();
        customerList = new ArrayList<>();
        this.revenue = 0.00;
        this.ratings = new ArrayList<>();
        // restaurantCount ++;
        if (restaurantCount >= SystemLimits.MAXIMUM_INSTANCES) {
            throw new MaxInstancesException("More than 100 restaurants have been created");
        }
        restaurantCount++;
    }

    @Override
    public String toString() {
        String r = "Restaurant " + name + "\n";
        r += " Address: " + address + "\nPhone number: " + phoneNumber + "\n";
        r += "Ratings: " + "\n";
        if (ratings.size() == 0) {
            r += "No ratings have been left yet." + "\n";
        } else {
            for (Rating rating : ratings) {
                r += rating.toString();
            }
        }
        r += "-----------------------------------------------\n";

        r += getName() + " revenue: " + UI.money(revenue) + "\n";

        r += "-----------------------------------------------\n";

        r += menu.toString();

        r += "-----------------------------------------------\n";

        r += customerList.toString() + "\n";
        r += staffList.toString() + "\n";
        return r;
    }

    public void startup() {
        String selection = "";
        while (!selection.equals("4")) {
            UI.printHeader(getName().toUpperCase());
            printInfo();
            UI.printSection("MAIN MENU");
            System.out.println("1) Customer");
            System.out.println("2) Employee Login");
            System.out.println("3) View Ratings");
            System.out.println("4) Quit");
            System.out.print("Selection: ");
            selection = Input.getString();
            System.out.println();

            switch (selection.toLowerCase()) {
                case "1":
                    customerPortal();
                    break;
                case "2":
                    employeePortal();
                    break;
                case "3":
                    printRatings();
                    break;
                case "4":
                    break;
                default:
                    UI.error("Invalid Choice");
                    break;
            }
        }
    }

    private void customerPortal() {
        UI.printSection("CUSTOMER LOGIN");
        System.out.print("Enter your phone number: ");
        String phoneNumber = Input.getString().trim();
        Customer customer = findCustomer(phoneNumber);
        try {
            if (customer == null) {
                UI.info("Phone number not found. Creating a new account.");
                System.out.print("Enter name: ");
                String name = Input.getString().trim();
                customer = new Customer(name, phoneNumber, this);
                addCustomer(customer);
                UI.success("Account created successfully.");
            } else {
                UI.success("Welcome back, " + customer.getName() + ".");
            }
            customer.customerDuties();
        } catch (MaxInstancesException e) {
            UI.error(e.getMessage());
        }

    }

    private void employeePortal() {
        UI.printSection("EMPLOYEE LOGIN");
        System.out.print("Enter your staff ID: ");
        String staffID = Input.getString().trim();

        try {
            Staff staff = findStaff(staffID);
            UI.success("Login successful. Welcome, " + staff.getName() + ".");
            staff.performDuties();
        } catch (StaffNotFoundException e) {
            UI.error(e.getMessage());
        }
    }

    public void printInfo() {
        System.out.println("Address: " + address + "\nPhone number: " + phoneNumber);
    }

    // Menu section
    public void createMenu() {
        Menu menu;
        try {
            menu = new Menu();
            setMenu(menu);
        } catch (MaxInstancesException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

    }

    // public Menu getMenu() {
    // return this.menu; // maybe not safe? return a copy?
    // }
    public FoodItem getMenuItem(int choice) throws MenuItemNotFoundException {
        return menu.getItem(choice);
    }

    public void addItemToMenu(FoodItem foodItem) {
        menu.addItem(foodItem);
    }

    public void removeItem(String name) {
        try {
            FoodItem change = menu.findItemByName(name);
            menu.removeItem(change);
            UI.success("Removed " + change.getName() + " from the menu.");
        } catch (MenuItemNotFoundException e) {
            UI.error(e.getMessage());
        }

    }

    public void changePrice(String name, double price) {
        try {
            FoodItem change = menu.findItemByName(name);
            menu.changeItemPrice(change, price);
            UI.success("Changed " + change.getName() + "'price to " + UI.money(change.getPrice()));
        } catch (MenuItemNotFoundException e) {
            UI.error(e.getMessage());
        }

    }

    public void showMenu() {
        UI.printHeader(getName() + " Menu");
        System.out.println(menu);
        System.out.println("-----------------------------------------------");
    }

    // Order section
    public void addOrder(Order order) {
        orders.add(order);
    }

    public void viewOrders() throws OrderNotFoundException {
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found.");
        }

        for (Order o : orders) {
            o.printOrder();
            System.out.println();
        }
    }

    public Order findOrder(int orderID) throws OrderNotFoundException {
        for (Order o : orders) {
            if (o.getOrderNumber() == orderID) {
                return o;
            }
        }
        throw new OrderNotFoundException("Order with ID " + orderID + " not found.");
        // return null;
    }

    public void processOrder(Order order) {
        addToRevenue(order.calculateTotal());
        addOrder(order);
    }

    // Rating section
    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
    }

    public void printRatings() {
        UI.printHeader("RATINGS");
        if (ratings.size() == 0) {
            UI.info("No ratings have been left yet.");
        } else {
            for (Rating r : ratings) {
                r.printRating();
                System.out.println();
            }
        }
    }

    // Revenue section
    public void addToRevenue(double amount) {
        this.revenue += amount;
    }

    public void printRevenue() {
        System.out.println(getName() + " revenue: " + UI.money(revenue));
    }

    // Employee section
    public void hireEmployee(Staff staff) {
        staffList.add(staff);
    }

    public Staff findStaff(String staffID) throws StaffNotFoundException {
        for (Staff s : staffList) {
            if (s.getStaffID().equals(staffID)) {
                return s;
            }
        }
        throw new StaffNotFoundException("Staff with ID: " + staffID + " does not exist.");
    }

    public boolean checkStaff(String staffID) {
        for (Staff s : staffList) {
            if (s.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;

    }

    public ArrayList<Staff> getStaffList() {
        return new ArrayList<>(staffList);
    }

    public void fireStaff(Staff staff) {
        staffList.remove(staff);
    }

    // Customer section
    public Customer findCustomer(String phoneNumber) {
        for (Customer c : customerList) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                return c;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // Rest of getters and setters
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public ArrayList<Customer> getCustomerList() {
        return new ArrayList<>(customerList);
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = new ArrayList<>(customerList);
    }

    // Orders
    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    // Revenue
    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    // Ratings
    public ArrayList<Rating> getRatings() {
        return new ArrayList<>(ratings);
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = new ArrayList<>(ratings);
    }
}
