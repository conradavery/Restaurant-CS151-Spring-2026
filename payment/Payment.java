package payment;

import java.util.Iterator;
/*

Need to implement:
- remove methodType? (overcomplicates)
- isntead of returning booleans, return exceptions so you know what went wrong
- further security with payment manager
*/

public class Payment {
    private class PaymentIterator implements Iterator<Integer>{
        private int count = 0;

        public boolean hasNext(){
            return true;
        }

        public Integer next(){
            count += 1;
            return count;
        }
    
    }

    private int paymentId;
    private Wallet from;
    private Wallet to;
    private double amount;
    private String methodType;
    private boolean isSuccessful;
    private final PaymentIterator iter = new PaymentIterator();

    public Payment(Wallet from, Wallet to, double amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.paymentId = iter.next();
    }

    public boolean processPayment(double amount){
        double diff = this.amount - amount;
        boolean status = diff <= 0 ? true: false;
        double leftover = Math.abs(diff);
        this.isSuccessful = status;
        // not processed, then failed. 
        if (!status) return status;

        // send the change over back to T
        return this.to.sendMoneyTo(from, amount);

    }

    public boolean refund(){
        return this.from.sendMoneyTo(to, amount);

    }

    public String generateString(){
        return String.format("PaymentID: %d\nAmount Requested: %0.2f\nCurrentStatus: %b\n", paymentId, amount, isSuccessful);
    }


}