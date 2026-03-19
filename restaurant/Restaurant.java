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
    private ArrayList<Staff> staffList; 
    // private ArrayList<Order> orders; 
    private double revenue;
    // private ArrayList<Rating> ratings; Implement later

    Scanner scanner = new Scanner(System.in);

    public Restaurant(String name){ //Only need a name to create a resturant
        this.name = name;
        this.address = null;
        this.menu = null;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        if (this.address == null){

            return "No Address Set";
        }
        return this.address;
    }
    public void createMenu(){
        Menu menu = new Menu();
        this.menu = menu;
    }
    public void addItemToMenu(){
        if (this.menu == null){
            System.out.println("Menu not created yet, please create a menu first.");
            return;
        }
        menu.addItem();
    }
    public void removeItem(){
        if (this.menu == null){
            System.out.println("Menu not created yet, please create a menu first.");
            return;
        }
        System.out.println("What is the name of the item to remove from the menu: ");
        String name = scanner.nextLine();
        menu.removeItem(name);
    }
    public void showMenu(){
        if (this.menu == null){
            System.out.println("Menu not created yet, please create a menu first.");
            return;
        }
        System.out.println(this.name + " Menu:");
        menu.printMenu();
    }
    public void hireNewStaff(){
        System.out.println("What is the Staff name");
        String name = scanner.nextLine();
        System.out.println("What is the staff's role");
        String role = scanner.nextLine();
        System.out.println("What is the staffs salary");
        Double salary = scanner.nextDouble();
        Staff staff = new Staff(name, role, salary);
        staffList.add(staff);
    }
    @Override
    public String toString(){
        return this.name + " located at: " + this.address;
    }

    
}
