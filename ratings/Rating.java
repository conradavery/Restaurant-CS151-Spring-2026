package ratings;

import users.Customer;
public class Rating {
    
    private int stars;
    private String message;
    private Customer customer;

    public Rating(Customer customer, int stars, String message){
        this.customer = customer;
        this.stars = stars;
        this.message = message;
    }
    public void printRating(){
        System.out.println(customer.getName() + " - " + this.stars);
        System.out.println(message);
    }
}
