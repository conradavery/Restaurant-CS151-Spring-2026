package payment;

import order.Order;
import utilities.Input;
import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.InvalidCreditInfoException;

public class CardPayment implements Payable {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    public static int cardPaymentCount = 0;

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
        setCardNumber(Input.getString());

        System.out.print("Enter card holder name: ");
        setCardHolder(Input.getString());

        System.out.print("Enter expiry date (MM/YY): ");
        setExpiryDate(Input.getString());

        System.out.print("Enter CVV: ");
        setCVV(Input.getString());

        System.out.println();
        UI.info("Processing card payment...");

        try{
            processCreditInfo();
            UI.success("Payment successful!");
            order.setStatusPaid();
            return true;
        } catch (InvalidCreditInfoException e){
            UI.error("Payment unsuccessful");
            order.setStatusPaymentDenied();
            return false;
        }
        
    }

    public void processCreditInfo() throws InvalidCreditInfoException{
        // null
        if (cardNumber == null)
            throw new InvalidCreditInfoException("Empty cardNumber");
        if (expiryDate == null)
            throw new InvalidCreditInfoException("Empty expiryDate");
        if (cardHolder == null)
            throw new InvalidCreditInfoException("Empty cardHolder");
        if (cvv == null)
            throw new InvalidCreditInfoException("Empty cvv");

        // general formatting errors
        String matchString = "^(0?[1-9]|1[0-2])/\\d{2}$";
        if (cardNumber.length() != 16)
            throw new InvalidCreditInfoException("Invalid cardNumber, not long enough");
        if (cvv.length() != 3)
            throw new InvalidCreditInfoException("Invalid CVV");
        if (!expiryDate.matches(matchString))
            throw new InvalidCreditInfoException("Invalid expiry date");
    }

    @Override
    public void generateReceipt(Order order) {
        UI.printHeader("RECEIPT");
        order.printOrder();
        System.out.println(this);
    }

    @Override
    public String toString() {
        String r = "Payment method: CARD" + "\n";
        r += "Card Holder: "+ getCardHolder() + "\n";
        r += "Last 4 digits: " + getCardNumber().substring(12, 16) + "\n";
        return r;
    }

}
