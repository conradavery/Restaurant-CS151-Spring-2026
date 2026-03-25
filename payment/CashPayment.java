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


    public double getBillTotal(){
        return billTotal;
    }
    public double getCashPaid(){
        return cashPaid;
    }
    public double getChange(){
        return change;
    }

    public void setBillTotal(double newBillTotal){
        billTotal = newBillTotal;
    }
    public void setCashPaid(double newCashPaid){
        cashPaid = newCashPaid;
    }
    public void setChange(double newChange){
        change = newChange;
    }

    @Override
    public boolean validatePayment(Order order) {
        this.billTotal = order.calculateTotal();
        this.cashPaid = 0;
        while (cashPaid < billTotal) {
            System.out.print("\nEnter cash amount (or enter 0 to change payment/cancel order): ");
            try {
                cashPaid = Input.getDouble();

                if(cashPaid == 0){
                    order.setStatusPaymentDenied();
                    return false;
                }
                if (cashPaid < billTotal) {
                    UI.error("Not enough cash. Please pay at least " + billTotal);
                }
            } catch (NumberFormatException e) {
                UI.error("Enter cash amount as a double");
            }
        }

        this.change = cashPaid - billTotal;
        UI.info("Payment successful!");
        UI.info("Change: " + UI.money(change));
        order.setStatusPaid();
        return true;
    }

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println(this);
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

    @Override
    public String toString() {
        String r = "Payment method: CASH" + "\n";
        r += "Paid with: " + UI.money(cashPaid) + "\n";
        r += "Change: " + UI.money(change) + "\n";
        return r;
    }
}
