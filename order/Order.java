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
        calculateTotal();
    }
    // public void removeItem(String itemId) {
    //     for (int i = 0; i < items.size(); i++) {
    //         if (items.get(i).getMenuItemId().equals(itemId)) {
    //             items.remove(i);
    //             break; // remove only the first match
    //         }
    //     }
    //     calculateTotal();
    // }
    public void payWithCash(){
        this.paymentMethod = new CashPayment();
        paymentMethod.processPayment(this);
    }
    public void payWithCard(){
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
    public int getOrderNumber(){
        return this.orderNumber;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void cancelOrder() {
        this.status = "Cancelled";
    }

    //    public void applyDiscount(double discountPercentage) {
    //         calculateTotal();
    //         double discountAmount = totalPrice * (discountPercentage / 100.0);
    //         totalPrice -= discountAmount;
    //     }

    // public void assignStaff (Staff staffMember) {
    //     this.staffMember = staffMember;
    // }

    public void updateStatus(String status) {
        this.status = status;
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

    // public void setStaffMember(Staff staffMember) {
    //     this.staffMember = staffMember;
    // }
}