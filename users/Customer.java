package users;

public class Customer {

    private Object order ;
    private String name;
    private Object payment;

    public Customer(String name){
        this.name = name;
    }

    public void createOrder() {
        System.out.println("USER: " + name + " started a new order.");
    }

    public void addItemtoOrder() {
        System.out.println("USER: Item added to cart.");
    }

    public void cancelOrder() {
        this.order = null;
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
