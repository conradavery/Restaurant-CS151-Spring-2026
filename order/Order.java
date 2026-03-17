package order;

import users.Customer;
import users.Staff;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;
    private List<OrderItem> items;
    private double price;
    private String status;
    private int orderNumber;
    private Staff staffMember;

     public Order(Customer customer, List<OrderItem> items, String status, int orderNumber, Staff staffMember) {
        this.customer = customer;
        this.items = (items != null) ? items : new ArrayList<>();
        this.status = status;
        this.orderNumber = orderNumber;
        this.staffMember = staffMember;
        this.price = calculatePrice();
    }

    public double calculatePrice() {
        double totalPrice = 0.0;
        for (OrderItem item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        this.price = totalPrice;
        return this.price;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        calculatePrice();
    }

    public boolean removeItem(OrderItem item) {
        boolean removed = items.remove(item);
        if (removed) {
            calculatePrice();
        }
        return removed;
    }

   public void applyDiscount(double discountPercentage) {
        calculatePrice();
        double discountAmount = price * (discountPercentage / 100.0);
        price -= discountAmount;
    }

    public void assignStaff (Staff staffMember) {
        this.staffMember = staffMember;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Staff getStaffMember() {
        return staffMember;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
        calculatePrice();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setStaffMember(Staff staffMember) {
        this.staffMember = staffMember;
    }
}