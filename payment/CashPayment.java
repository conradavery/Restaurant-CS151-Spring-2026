package payment;

import order.Order;
import utilities.Input;
import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;

public class CashPayment implements Payable {

    private double billTotal;
    private double cashPaid;
    private double change;
    private static int cashPaymentCount = 0;

    public CashPayment() throws MaxInstancesException{
        if(cashPaymentCount >= SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 cash payments have been created");
        }
        cashPaymentCount ++;
    }

    @Override
    public boolean validatePayment(Order order) {
        this.billTotal = order.calculateTotal();
        this.cashPaid = 0;
        while (cashPaid < billTotal) {
            System.out.print("\nEnter cash amount: ");
            try {
                cashPaid = Input.getDouble();

                if (cashPaid < billTotal) {
                    UI.error("Not enough cash. Please pay at least " + billTotal);
                }
            } catch (NumberFormatException e) {
                UI.error("Enter cash amount as a double");
            }
        }

        this.change = cashPaid - billTotal;
        UI.info("Payment successful!");
        UI.info("Change: " + change);
        order.setStatusPaid();
        return true;
    }

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println("Payment method: CASH");
        System.out.println("Paid with: " + UI.money(cashPaid));
        System.out.println("Change: " + UI.money(change));
    }

    @Override
    public void processPayment(Order order) {
        UI.printHeader("CASH PAYMENT");
        UI.printSection("Order total");
        System.out.print("Your order total is: " + UI.money(order.calculateTotal()));
        if (validatePayment(order)) {
            generateReceipt(order);
        }
    }
}
