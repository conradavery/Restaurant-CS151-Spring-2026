package utilities;

import order.Order;

public class CashPayment implements Payable{

    private int paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isPaid;

    public CashPayment(int paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = "Cash";
        this.isPaid = false;
    }

    @Override
    public void processPayment() {
        if (!validatePayment()){
            System.out.println("Payment validation failed. Cannot process payment.");
            return;
        }
        
        System.out.println("Processing cash payment...");
        isPaid = true;
    }

    @Override
    public void refund() {
        if (!isPaid){
            System.out.println("Payment has not been made. Cannot process refund.");
            return;
        }
        System.out.println("Refunding cash payment...");
        isPaid = false;
    }

    @Override
    public boolean validatePayment() {
        return amount > 0;
    }

    @Override
    public void markPaid() {
        isPaid = true;
    }

    @Override
    public String generateReceipt(Order order) {
        return "Receipt\n" +
            "Order ID: " + order.getOrderId() + "\n" +
            "Payment ID: " + paymentId + "\n" +
            "Method: " + paymentMethod + "\n" +
            "Amount: $" + amount + "\n" +
            "Status: " + (isPaid ? "Paid" : "Unpaid");
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getAmount() {
        return amount;
    }
}
