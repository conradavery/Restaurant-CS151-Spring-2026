// Gift Card Payment
package payment;

import order.Order;
import utilities.Input;
import utilities.UI;
import utilities.exceptions.InvalidCreditInfoException;

public class GiftCardPayment implements Payable {
    //Variables Declaration
    private String receipt;
    private String expirationDate;
    @Override
    public boolean validatePayment(Order order) {   
    return giftCardNumber != null && pin != null && hasSufficientBalance(order.getTotalPrice());
    }
    private String pin;

    private String giftCardNumber;
    private double balance;

    // Simulate entering gift card info at kiosk
    public void collectPaymentDetails() throws InvalidCreditInfoException {
        UI.printHeader("GIFT CARD PAYMENT");
        
        // Gift Card number
        System.out.print("Enter gift card number: ");
        giftCardNumber = Input.getString();
        
        System.out.print("Enter gift card PIN: ");
        pin = Input.getString(); 
        // Expiration Date
        System.out.print("Enter expiration date (MM/YY): ");
        expirationDate = Input.getString();
        validate();
        loadBalance(); // simulate fetching from system
    }

    // Gift Card number length should be 16, and it shouldn't be shorter, otherwise it doesn't work.
    private void validate() throws InvalidCreditInfoException {
        if (giftCardNumber == null || giftCardNumber.length() < 16) { //
            throw new InvalidCreditInfoException("Invalid gift card code.");
        }
        if (pin == null || pin.length() != 4 || !pin.matches("\\d{4}")) {
            throw new InvalidCreditInfoException("Invalid PIN. Must be 4 digits.");
        }
    }

    // Gift Card Balnace
    private void loadBalance() {
        // Example with 100 gift card balance
        balance = 100.00; // put card to 100
    }
    // If there is enough funds in the gift card
    public boolean hasSufficientBalance(double amount) {
    return balance >= amount;
}

    // Process payment
    @Override
    public void processPayment(Order order) {   
        double total = order.getTotalPrice();

        if (balance >= total) {
            balance -= total;
    } else {
        System.out.println("Insufficient gift card balance.");
    }
}

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Payment method: GIFT CARD\n" +
               "Card Code: ****" + giftCardNumber.substring(giftCardNumber.length() - 4) + "\n" +
               "Remaining Balance: $" + balance;
    }

}

