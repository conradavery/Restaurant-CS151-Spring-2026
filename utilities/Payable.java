package utilities;

import order.Order;

public interface Payable {

    void processPayment();

    void refund();

    boolean validatePayment();

    void markPaid();

    String generateReceipt(Order order);

    String getPaymentMethod();
}
