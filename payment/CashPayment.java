package payment;

import java.util.Scanner;

import order.Order;
import utilities.UI;

public class CashPayment implements Payable{

    private double billTotal;
    private double cashPaid;
    private double change;
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public boolean validatePayment(Order order) {
        this.billTotal = order.calculateTotal();
        this.cashPaid = 0;
        while (cashPaid < billTotal) {
            System.out.print("\nEnter cash amount: ");
            cashPaid = scanner.nextDouble();

            if (cashPaid < billTotal) {
                UI.error("Not enough cash. Please pay at least " + billTotal);
            }
        }

        this.change = cashPaid - billTotal;
        UI.info("Payment successful!");
        UI.info("Change: " + change);
        order.setStatus("PAID");
        return true;
    }

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println("Payment method: CASH");
        System.out.println("Paid with: "+ UI.money(cashPaid));
        System.out.println("Change: " + UI.money(change));
    }

    @Override
    public void processPayment(Order order) {
        UI.printHeader("CASH PAYMENT");
        UI.printSection("Order total");
        System.out.print("Your order total is: " + UI.money(order.calculateTotal()));
        if (validatePayment(order)){
            generateReceipt(order);
        }
    }
}
