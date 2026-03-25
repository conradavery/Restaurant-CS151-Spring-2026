package order;

import java.util.ArrayList;
import menuAndFoodItems.FoodItem;
import payment.CardPayment;
import payment.CashPayment;
import payment.Payable;
import utilities.*;
import utilities.exceptions.MaxInstancesException;

public class Order {

    private static int instanceCounter = 0;

    private ArrayList<FoodItem> items;
    private double totalPrice;
    private String status;
    private int orderNumber;
    private Payable paymentMethod;
    // private Staff staffMember;

    public Order() throws MaxInstancesException {
        items = new ArrayList<>();
        instanceCounter++;
        this.orderNumber = instanceCounter;
        this.paymentMethod = null;
        if(instanceCounter > SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 orders have been created");
        }
    }

    public void addItemToOrder(FoodItem item) {
        items.add(item);
        UI.success("Added "+ item.getName() + " to order.");
        calculateTotal();
    }

    public void removeItemByName(String name) {
        boolean removed = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            UI.success("Item removed from order.");
            calculateTotal();
        } else {
            UI.error("Item not found in order.");
        }
        
    }

    public void payWithCash() {
        try{
            this.paymentMethod = new CashPayment();
            paymentMethod.processPayment(this);
        } catch (MaxInstancesException e){
            UI.error(e.getMessage());
        }
    }

    public void payWithCard() {
        try {
            this.paymentMethod = new CardPayment();
            paymentMethod.processPayment(this);
        } catch (MaxInstancesException e) {
            UI.error(e.getMessage());
        }
    }

    public double calculateTotal() {
        double totalPrice = 0.0;
        for (FoodItem item : items) {
            totalPrice += item.getPrice();
        }
        this.totalPrice = totalPrice;
        return this.totalPrice;
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public int getOrderNumber() {
        return this.orderNumber;
    }
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }
    public void setStatusPreparing(){
        this.status = "PREPARING";
    }
    public void setStatusComplete(){
        this.status = "COMPLETE";
    }
    public void setStatusPaid(){
        this.status = "PAID";
    }
    public void setStatusPaymentDenied(){
        this.status = "PAYMENT DENIED";
    }
    public void setStatusInProgress(){
        this.status = "IN PROGRESS";
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatusCancelled() {
        this.status = "CANCELLED";
    }
    public int getOrderLength(){
        return items.size();
    }

    public void printOrder() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String r = "ORDER NUMBER: " + getOrderNumber() + "\n";
        for (FoodItem f : items) {
            r += f;
        }
        r+= "-----------------------------------------------\n";
        r += String.format("%-25s %10s%n", "Total cost:", UI.money(getTotalPrice()));
        r += "STATUS: " + this.status;

        return r;
    }

}