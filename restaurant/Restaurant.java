package restaurant;

import java.util.ArrayList;
import menuAndFoodItems.*;
import java.util.Scanner;
import users.*;
import order.Order;
import utilities.UI;
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
    // private static int restaurantCount = 0;

    Scanner scanner = new Scanner(System.in);

    public Restaurant(String name, String address, String phoneNumber){ 
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
    }
    public Menu getMenu(){
        return this.menu; //maybe not safe? return a copy?
    }
    public void printInfo(){
        System.out.println("Address: " + address + "\nPhone number: " + phoneNumber);
    }
    public void addRating(Rating rating){
        ratings.add(rating);
    }
    public void removeRating(Rating rating){
        ratings.remove(rating);
    }
    public void printRatings(){
        UI.printHeader("RATINGS");
        if (ratings.size() == 0){
            UI.info("No ratings have been left yet.");
        }
        else{
            for (Rating r: ratings){
                r.printRating();
                System.out.println();
            }
        }
    }
    @Override
    public String toString(){
        return this.name + " Address: " + this.address + " Phone Number: " + this.phoneNumber;
    }
    public void addToRevenue(Double amount){
        this.revenue += amount;
    }
    public void printRevenue(){
        System.out.println(getName() + " revenue: " + UI.money(revenue));
    }
    public void createMenu(){
        Menu menu = new Menu();
        this.menu = menu;
    }
    public String getName(){
        return this.name;
    }
    public void addItemToMenu(FoodItem foodItem){
        menu.addItem(foodItem);
    }
    public void removeItem(String name){
        menu.removeItem(name);
    }
    public void showMenu(){
        UI.printHeader(getName() + " Menu");
        menu.printMenu();
    }
    public void hireEmployee(Staff staff){
        staffList.add(staff);
    }
    public Staff findStaff(String staffID){
        for (Staff s: staffList){
            if (s.getStaffID().equals(staffID)){
                return s;
            }
        }
        return null;
    }
     public ArrayList<Staff> getStaffList() {
      return staffList;
    }
    public void fireStaff(Staff staff){
        staffList.remove(staff);
    } 
    public Customer findCustomer(String phoneNumber){
        for (Customer c: customerList){
            if (c.getPhoneNumber().equals(phoneNumber)){
                return c;
            }
        }
        return null;
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public void viewOrders(){
        for (Order o: orders){
            o.printOrder();
            System.out.println();
        }
    }
    public Order findOrder(int orderID){
        for (Order o: orders){
            if(o.getOrderNumber() == orderID){
                return o;
            }
        }
        return null;
    }
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }
}
