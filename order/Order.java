package order;

import java.util.ArrayList;
import menuAndFoodItems.FoodItem;
public class Order{

    // private String orderID;
    // private Customer customer;
    private ArrayList<FoodItem> items;
    private String status;
    private static int orderCount = 0;
    private int orderNumber;

    public Order(){
        items = new ArrayList<>();
        orderCount ++;
        this.orderNumber = orderCount;
    }
    public void addItemToOrder(FoodItem item){
        items.add(item);
    }
    public int getOrderNumber(){
        return this.orderNumber;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public double calculateTotal(){
        double total = 0.00;
        for(FoodItem f: items){
             total += f.getPrice();
        }
        return total;
    }
    public void printOrder(){
        System.out.println("ORDER NUMBER: " + getOrderNumber());
        for(FoodItem f: items){
            System.out.println(f.getName() + "\t" + "$"+ f.getPrice());
        }
        System.out.println("Total cost: $" + calculateTotal());
        System.out.println("STATUS: " + this.status);
    }

}