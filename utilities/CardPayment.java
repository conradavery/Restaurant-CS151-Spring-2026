package utilities;

import order.Order;

public class CardPayment implements Payable {

    private int paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isPaid;

    public CardPayment(int paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = "Card";
        this.isPaid = false;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing card payment...");
        isPaid = true;
    }

    @Override
    public void refund() {
        System.out.println("Refunding card payment...");
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
