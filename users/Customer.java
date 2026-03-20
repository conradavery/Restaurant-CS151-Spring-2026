package users;

import restaurant.*;

public class Customer {

    // private Object order ;
    // private ArrayList<Order> orders;
    private String name;
    // private Order currentOrder;
    private Object payment;
    private String phoneNumber;
    private Restaurant restaurant;


    public Customer(String name, String phoneNumber, Restaurant restaurant){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.restaurant = restaurant;
        // this.currentOrder = null;
        // orders = new ArrayList<>();
    }

    public void createOrder() {
        System.out.println("USER: " + name + " started a new order.");
        // currentOrder = new Order();
        restaurant.showMenu();

    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void addItemtoOrder() {
        System.out.println("USER: Item added to cart.");
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
    public void viewOrders(){
        // for(Order o: orders){
        //     System.out.println(o);
        // }
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
