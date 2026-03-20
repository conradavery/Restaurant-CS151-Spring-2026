package order;

import users.*;
import java.util.ArrayList;
import menuAndFoodItems.FoodItem;
public class Order{

    // private String orderID;
    // private Customer customer;
    private ArrayList<FoodItem> items;
    private String status;

    public Order(){
        items = new ArrayList<>();
    }
    public void addItemToOrder(FoodItem item){
        items.add(item);
    }
    public void setStatus(String status){
        this.status = status;
    }
    public double calculateTotal(){
        double total = 0.00;
        for(FoodItem f: items){
             total += f.getPrice();
        }
        return total;
    }
    public void printOrder(){
        for(FoodItem f: items){
            System.out.println(f.getName() + "\t" + "$"+ f.getPrice());
        }
        System.out.println("Total cost: $" + calculateTotal());
        System.out.println("STATUS: " + this.status);
    }

}