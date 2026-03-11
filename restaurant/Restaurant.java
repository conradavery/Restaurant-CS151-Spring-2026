package restaurant;

import java.util.ArrayList;
import menuAndFoodItems.*;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private String address;
    private Menu menu;
    // private ArrayList<Staff> staff; Implement later
    // private ArrayList<Order> orders; Implement later
    private double revenue;
    // private ArrayList<Rating> ratings; Implement later

    Scanner scanner = new Scanner(System.in);

    public Restaurant(String name){ //Only need a name to create a resturant
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void createMenu(){
        Menu menu = new Menu();
        this.menu = menu;
    }
    public void addItemToMenu(){
        menu.addItem();
    }
    public void removeItem(){
        System.out.println("What is the name of the item to remove from the menu: ");
        String name = scanner.nextLine();
        menu.removeItem(name);
    }
    public void showMenu(){
        System.out.println(this.name + " Menu:");
        menu.printMenu();
    }
    // Need to replicate this for like everything else
    // eventually need to put a bunch of things into a try catch so if the wrong value is inputed then it doesn't break. 


    public static void main(String [] args){//main method here for testing for now
        Restaurant test = new Restaurant("Test Resturant");
        test.createMenu();
        test.addItemToMenu();
        test.showMenu();
        test.addItemToMenu();
        test.showMenu();
        test.removeItem();
        test.showMenu();
    }
}
