package utilities;

import order.Order;

public class CashPayment implements Payable{

    private int paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isPaid;

    @Override
    public void processPayment(){
        System.out.println("Processing cash payment...");
        isPaid = true;
    }

    @Override
    public void refund() {
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
        return "Cash Payment Receipt for Order: " + order.getOrderId();
    }
}
