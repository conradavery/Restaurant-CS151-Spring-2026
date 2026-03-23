package order;

import users.Customer;
import users.Staff;
import java.util.ArrayList;
import menuAndFoodItems.FoodItem;
import utilities.UI;

public class Order {

import payment.Payment;
import utilities.SystemLimits;

public class Order {

    private static int instanceCounter = 0;

    private String orderId;
    private Customer customer;
    private List<MenuItem> items;
    private double totalPrice;
    private String orderType;
    private String status;
    private Payment payment;
    private int orderNumber;
    // private Staff staffMember;


    public Order() {
        items = new ArrayList<>();
        orderCount++;
        this.orderNumber = orderCount;
    }

    public void addItemToOrder(FoodItem item) {
        items.add(item);
        calculateTotal();
    }
    public void removeItem(String itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getMenuItemId().equals(itemId)) {
                items.remove(i);
                break; // remove only the first match
            }
        }
        calculateTotal();
    }

    public double calculateTotal() {
        double totalPrice = 0.0;
        for (MenuItem item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        this.totalPrice = totalPrice;
        return this.totalPrice;
    }

    public void submitOrder() {
        this.status = "Submitted";
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