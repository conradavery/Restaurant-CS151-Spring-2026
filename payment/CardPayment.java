package payment;

import java.util.Scanner;

import order.Order;
import utilities.UI;

public class CardPayment implements Payable {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void processPayment(Order order) {
        UI.printHeader("CARD PAYMENT");
        UI.printSection("Order total");
        System.out.print("Your order total is: " + UI.money(order.calculateTotal()));
        if (validatePayment(order)){
            generateReceipt(order);
        }
    }

    @Override
    public boolean validatePayment(Order order) {
        System.out.println();
        System.out.print("Enter card number: ");
        this.cardNumber = scanner.nextLine();

        System.out.print("Enter card holder name: ");
        this.cardHolder = scanner.nextLine();

        System.out.print("Enter expiry date (MM/YY): ");
        this.expiryDate = scanner.nextLine();

        System.out.print("Enter CVV: ");
        this.cvv = scanner.nextLine();

        System.out.println();
        UI.info("Processing card payment...");
        if(processCreditInfo()){
            UI.success("Payment successful!");
            order.setStatusPaid();
            return true;
        } else{
            UI.error("Payment unsuccessful");
            order.setStatusPaymentDenied();
            return false;
        }
        
    }

    private boolean processCreditInfo(){
        // Hypothetical method for this assignement but could be a real method that checks if a credit card is valid
        if (cardNumber.equals(cardNumber) && cardHolder.equals(cardHolder) && expiryDate.equals(expiryDate) && cvv.equals(cvv)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println("Payment method: CARD");
        System.out.println("Card Holder: "+ cardHolder);
        System.out.println("First 4 digits: " + cardNumber.substring(0, 4));
    }
    

}
