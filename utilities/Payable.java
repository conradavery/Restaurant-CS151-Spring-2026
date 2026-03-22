package utilities;

import order.Order;

public interface Payable {

    void processPayment(double amount);

    void refund();

    boolean validatePayment();

    void markPaid();

    String generateReceipt(Order order);
}
