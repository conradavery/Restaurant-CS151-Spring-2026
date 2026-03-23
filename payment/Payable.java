package payment;

import order.Order;

public interface Payable {

    void processPayment(Order order);

    boolean validatePayment(Order order);

    void generateReceipt(Order order);

}
