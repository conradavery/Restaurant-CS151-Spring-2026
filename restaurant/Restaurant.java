package restaurant;

import java.util.ArrayList;
import menuAndFoodItems.*;
import java.util.Scanner;
import users.*;
// import order.*;

public class Restaurant {
    private String name;
    private String address;
    private Menu menu;
    private String phoneNumber;
    private ArrayList<Staff> staffList;
    private ArrayList<Customer> customerList;
    // private ArrayList<Order> orders; 
    private double revenue;
    // private ArrayList<Rating> ratings; Implement later
    private static int restaurantCount = 0;

    Scanner scanner = new Scanner(System.in);

    public Restaurant(String name, String address, String phoneNumber){ 
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menu = null;
        staffList = new ArrayList<>();
        customerList = new ArrayList<>();
        restaurantCount ++;
    }

    @Override
    public String toString(){
        return this.name + " Address: " + this.address + " Phone Number: " + this.phoneNumber;
    }
    public void createMenu(){
        Menu menu = new Menu();
        this.menu = menu;
    }
    public void addItemToMenu(FoodItem foodItem){
        menu.addItem(foodItem);
    }
    public void removeItem(String name){
        menu.removeItem(name);
    }
    public void showMenu(){
        System.out.println(this.name + " Menu:");
        menu.printMenu();
    }
    public void hireEmployee(Staff staff){
        staffList.add(staff);
    }
    public Customer findCustomer(String phoneNumber){
        for (Customer c: customerList){
            if (c.getPhoneNumber().equals(phoneNumber)){
                return c;
            }
        }
        return null;
    }
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    public void takeOrder(){
        
    }

    
}
