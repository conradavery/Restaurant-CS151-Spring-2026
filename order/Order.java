package order;

import java.util.ArrayList;
import menuAndFoodItems.FoodItem;
import payment.CardPayment;
import payment.CashPayment;
import payment.Payable;
import utilities.*;

public class Order {

    private static int instanceCounter = 0;

    private ArrayList<FoodItem> items;
    private double totalPrice;
    private String status;
    private int orderNumber;
    private Payable paymentMethod;
    // private Staff staffMember;

    public Order() {
        items = new ArrayList<>();
        instanceCounter++;
        this.orderNumber = instanceCounter;
        this.paymentMethod = null;
    }

    public void addItemToOrder(FoodItem item) {
        items.add(item);
        UI.success("Added "+ item.getName() + " to order.");
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
        } else {
            UI.error("Item not found in order.");
        }
    }

    public void payWithCash() {
        this.paymentMethod = new CashPayment();
        paymentMethod.processPayment(this);
    }

    public void payWithCard() {
        this.paymentMethod = new CardPayment();
        paymentMethod.processPayment(this);
    }

    public double calculateTotal() {
        double totalPrice = 0.0;
        for (FoodItem item : items) {
            totalPrice += item.getPrice();
        }
        this.totalPrice = totalPrice;
        return this.totalPrice;
    }

    public void submitOrder() {
        this.status = "Submitted";
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }
    public int getOrderLength(){
        return items.size();
    }

    public void printOrder() {
        System.out.println("ORDER NUMBER: " + getOrderNumber());
        for (FoodItem f : items) {
            System.out.printf("%-25s %10s%n", f.getName(), UI.money(f.getPrice()));
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-25s %10s%n", "Total cost:", UI.money(calculateTotal()));
        System.out.println("STATUS: " + this.status);
    }

}