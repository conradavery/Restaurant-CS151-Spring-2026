package order;

import users.Customer;
import users.Staff;
import java.util.ArrayList;
import java.util.List;

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


    // public Order(Customer customer, List<MenuItem> items, String status, int orderNumber, Staff staffMember, String orderType) {
    //     this.customer = customer;
    //     this.items = (items != null) ? items : new ArrayList<>();
    //     this.status = status;
    //     this.orderNumber = orderNumber;
    //     this.staffMember = staffMember;
    //     this.price = calculatePrice();
    // }

    public Order(String orderId, Customer customer, List<MenuItem> items, String orderType, 
        String status, Payment payment, int orderNumber) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = (items != null) ? new ArrayList<>(items) : new ArrayList<>();
        this.orderType = orderType;
        this.status = status;
        this.payment = payment;
        this.orderNumber = orderNumber;
        this.totalPrice = calculateTotal();
    }

    public static Order createOrder(String orderId, Customer customer, List<MenuItem> items, String orderType, 
        String status, Payment payment, int orderNumber) {
        if (instanceCounter >= SystemLimits.MAXIMUM_INSTANCES) {
            throw new IllegalStateException("Maximum number of Order instances reached.");
        }
        instanceCounter++;
        return new Order(orderId, customer, items, orderType, status, payment, orderNumber);
    }

    public void addItem(MenuItem item) {
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

    public void attachPayment(Payment payment) {
        this.payment = payment;
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order Receipt\n");
        receipt.append("-------------\n");
        receipt.append("Customer: ").append(customer.getName()).append("\n");
        receipt.append("Order Type: ").append(orderType).append("\n");
        receipt.append("Items:\n");
        for (MenuItem item : items) {
            receipt.append("- ").append(item.getName()).append(" x").append(item.getQuantity()).append(" @ $").append(item.getPrice()).append("\n");
        }
        receipt.append("Total Price: $").append(totalPrice).append("\n");
        receipt.append("Payment Method: ").append(payment != null ? payment.getPaymentMethod() : "N/A").append("\n");
        receipt.append("Status: ").append(status).append("\n");
        return receipt.toString();
    }

    
    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    // public Staff getStaffMember() {
    //     return staffMember;
    // }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(List<MenuItem> items) {
        this.items = (items != null) ? new ArrayList<>(items) : new ArrayList<>();
        calculateTotal();
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    // public void setStaffMember(Staff staffMember) {
    //     this.staffMember = staffMember;
    // }
}