package payment;

import order.Order;
import utilities.Input;
import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;

public class CardPayment implements Payable {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    private static int cardPaymentCount = 0;

    public CardPayment() throws MaxInstancesException{
        if(cardPaymentCount >= SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 card payments have been created");
        }
        cardPaymentCount ++;
    }

    public String getCardNumber(){
        return cardNumber;
    }
    public String getCardHolder(){
        return cardHolder;
    }
    public String getExpiryDate(){
        return expiryDate;
    }
    public String getCVV(){
        return cvv;
    }

    // setters
    public void setCardNumber(String newCardNumber){
        cardNumber = newCardNumber;
    }
    public void setCardHolder(String newCardHolder){
        cardHolder = newCardHolder;
    }
    public void setExpiryDate(String newExpiryDate){
        expiryDate = newExpiryDate;
    }
    public void setCVV(String newCVV){
        cvv = newCVV;
    }

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
        this.cardNumber = Input.getString();

        System.out.print("Enter card holder name: ");
        this.cardHolder = Input.getString();

        System.out.print("Enter expiry date (MM/YY): ");
        this.expiryDate = Input.getString();

        System.out.print("Enter CVV: ");
        this.cvv = Input.getString();

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
        System.out.println("Card Holder: "+ getCardHolder());
        System.out.println("First 4 digits: " + getCardNumber().substring(0, 4));
    }
    

}
